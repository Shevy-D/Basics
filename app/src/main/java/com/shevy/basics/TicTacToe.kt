package com.shevy.basics

import androidx.annotation.IntRange
import androidx.annotation.Nullable
import androidx.core.util.Pair
import androidx.core.util.component1
import androidx.core.util.component2
import com.shevy.basics.TicTacToe.BoardState.Companion.MOVE_O
import com.shevy.basics.TicTacToe.BoardState.Companion.MOVE_X
import com.shevy.basics.TicTacToe.BoardState.Companion.SPACE


class TicTacToe {
    private lateinit var board: Array<IntArray>
    var playerToMove = BoardPlayer.PLAYER_X // stores whose turn it is
        private set

    @Nullable
    private var ticTacToeListener: TicTacToeListener? = null
    private var numberOfMoves = 0
    fun setTicTacToeListener(@Nullable ticTacToeListener: TicTacToeListener?) {
        this.ticTacToeListener = ticTacToeListener
    }

    fun isValidMove(x: Int, y: Int): Boolean {
        return board[x][y] == SPACE
    }

    fun moveAt(@IntRange(from = 0, to = 2) x: Int, @IntRange(from = 0, to = 2) y: Int): Boolean {
        require(!(x < 0 || x > BOARD_ROW - 1 || y < 0 || y > BOARD_COLUMN - 1)) {
            String.format(
                "Coordinates %d and %d are not valid, valid set [0,1,2]",
                x,
                y
            )
        }
        if (!isValidMove(x, y)) {
            return false
        }
        numberOfMoves++
        if (ticTacToeListener != null) {
            ticTacToeListener!!.movedAt(x, y, playerToMove.move)
        }
        board[x][y] = playerToMove.move
        val (first, second) = hasWon(x, y, playerToMove)
        if (first && ticTacToeListener != null) {
            ticTacToeListener!!.gameWonBy(playerToMove, second)
        } else if (numberOfMoves == BOARD_COLUMN * BOARD_ROW && ticTacToeListener != null) {
            ticTacToeListener!!.gameEndsWithATie()
        }
        changeTurnToNextPlayer()
        return true
    }

    private fun hasWon(
        x: Int,
        y: Int,
        playerToMove: BoardPlayer
    ): Pair<Boolean, Array<SquareCoordinates?>> {
        val winCoordinates = arrayOfNulls<SquareCoordinates>(3)
        val hasWon = (checkRow(x, y, playerToMove.move, winCoordinates)
                || checkColumn(x, y, playerToMove.move, winCoordinates)
                || checkDiagonals(x, y, playerToMove.move, winCoordinates))
        return Pair.create(hasWon, winCoordinates)
    }

    private fun checkDiagonals(
        x: Int,
        y: Int,
        move: Int,
        winCoordinates: Array<SquareCoordinates?>
    ): Boolean {
        if (board[0][0] == move && board[1][1] == move && board[2][2] == move) {
            winCoordinates[0] = SquareCoordinates(0, 0)
            winCoordinates[1] = SquareCoordinates(1, 1)
            winCoordinates[2] = SquareCoordinates(2, 2)
            return true
        } else if (board[0][2] == move && board[1][1] == move && board[2][0] == move) {
            winCoordinates[0] = SquareCoordinates(0, 2)
            winCoordinates[1] = SquareCoordinates(1, 1)
            winCoordinates[2] = SquareCoordinates(2, 0)
            return true
        }
        return false
    }

    private fun checkColumn(
        x: Int,
        y: Int,
        movetoCheck: Int,
        winCoordinates: Array<SquareCoordinates?>
    ): Boolean {
        for (i in 0 until BOARD_ROW) {
            if (board[i][y] != movetoCheck) {
                return false
            }
        }
        for (i in winCoordinates.indices) {
            winCoordinates[i] = SquareCoordinates(i, y)
        }
        return true
    }

    private fun checkRow(
        x: Int,
        y: Int,
        movetoCheck: Int,
        winCoordinates: Array<SquareCoordinates?>
    ): Boolean {
        for (i in 0 until BOARD_ROW) {
            if (board[x][i] != movetoCheck) {
                return false
            }
        }
        for (i in winCoordinates.indices) {
            winCoordinates[i] = SquareCoordinates(x, i)
        }
        return true
    }

    private fun changeTurnToNextPlayer() {
        playerToMove = if (playerToMove == BoardPlayer.PLAYER_X) {
            BoardPlayer.PLAYER_O
        } else {
            BoardPlayer.PLAYER_X
        }
    }

    private fun initGame() {
        board = Array(BOARD_ROW) {
            IntArray(
                BOARD_COLUMN
            )
        }
        playerToMove = BoardPlayer.PLAYER_X
        numberOfMoves = 0
    }

    fun resetGame() {
        initGame()
    }

    @BoardState
    fun getMoveAt(x: Int, y: Int): Int {
        return if (board[x][y] == SPACE) {
            SPACE
        } else if (board[x][y] == MOVE_O) {
            MOVE_O
        } else {
            MOVE_X
        }
    }

    @kotlin.annotation.Retention
    //@IntDef([SPACE, MOVE_X, MOVE_O])
    annotation class BoardState {
        companion object {
            var SPACE = 0
            var MOVE_X = 1
            var MOVE_O = 2
        }
    }

    enum class BoardPlayer(move: Int) {
        PLAYER_X(BoardState.MOVE_X), PLAYER_O(BoardState.MOVE_O);

        var move: Int = SPACE

        init {
            this.move = move
        }
    }

    interface TicTacToeListener {
        fun gameWonBy(boardPlayer: BoardPlayer?, winPoints: Array<SquareCoordinates?>)
        fun gameEndsWithATie()
        fun movedAt(x: Int, y: Int, move: Int)
    }

    // todo use this for passing coordinates
    class SquareCoordinates(// holds the row index of a Square on Board
        val i: Int, // holds the column index of a Square on Board
        val j: Int
    ) {
        override fun equals(o: Any?): Boolean {
            if (this === o) {
                return true
            }
            if (o == null || javaClass != o.javaClass) {
                return false
            }
            val that = o as SquareCoordinates
            return if (i != that.i) {
                false
            } else j == that.j
        }

        override fun hashCode(): Int {
            var result = i
            result = 31 * result + j
            return result
        }
    }

    companion object {
        const val BOARD_ROW = 3
        const val BOARD_COLUMN = 3
    }

    init {
        initGame()
    }
}