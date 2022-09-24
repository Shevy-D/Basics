package com.shevy.basics.ui

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import androidx.appcompat.app.AppCompatActivity
import com.shevy.basics.R
import com.shevy.basics.TicTacToe
import com.shevy.basics.customView.TicTacToeView
import com.shevy.basics.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), TicTacToe.TicTacToeListener,
    TicTacToeView.SquarePressedListener {
    lateinit var binding: ActivityMainBinding
    lateinit var ticTacToe: TicTacToe

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ticTacToe = TicTacToe()
        ticTacToe.setTicTacToeListener(this)
        ticTacToeView.squarePressListener = this

        resetButton.setOnClickListener {
            ticTacToe.resetGame()
            resetGameUi()
            resetButton.visibility = View.GONE
        }
    }

    override fun onSquarePressed(i: Int, j: Int) {
        ticTacToe.moveAt(i, j)
    }

    override fun movedAt(x: Int, y: Int, z: Int) {
        if (z == TicTacToe.BoardState.MOVE_X)
            ticTacToeView.drawXAtPosition(x, y)
        else
            ticTacToeView.drawOAtPosition(x, y)
    }

    override fun gameWonBy(
        boardPlayer: TicTacToe.BoardPlayer?,
        winCoords: Array<TicTacToe.SquareCoordinates?>
    ) {
        information.visibility = View.VISIBLE
        information.text = "Winner is ${if (boardPlayer?.move == TicTacToe.BoardState.MOVE_X) "X" else "O"}"
        ticTacToeView.animateWin(winCoords[0]!!.i, winCoords[0]!!.j, winCoords[2]!!.i, winCoords[2]!!.j)
        ticTacToeView.isEnabled = false
        resetButton.visibility = View.VISIBLE
    }

    override fun gameEndsWithATie() {
        information.visibility = View.VISIBLE
        information.text = getString(R.string.game_ends_draw)
        resetButton.visibility = View.VISIBLE
        ticTacToeView.isEnabled = false
    }

    private fun resetGameUi() {
        ticTacToeView.reset()
        ticTacToeView.isEnabled = true
        information.visibility = View.GONE
        resetButton.visibility = View.GONE
    }

/*    override fun gameWonBy(boardPlayer: TicTacToe.BoardPlayer, winCoords: Array<TicTacToe.SquareCoordinates>) {
        information.visibility = View.VISIBLE
        information.text = "Winner is ${if (boardPlayer.move == TicTacToe.BoardState.MOVE_X) "X" else "O"}"
        ticTacToeView.animateWin(winCoords[0].i, winCoords[0].j, winCoords[2].i, winCoords[2].j)
        ticTacToeView.isEnabled = false
        resetButton.visibility = View.VISIBLE
    }*/

}