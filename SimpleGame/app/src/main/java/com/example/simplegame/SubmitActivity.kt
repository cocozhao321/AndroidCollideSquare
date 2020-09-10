package com.example.simplegame

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

/*
Ke Zhao
CIS 195 Homework
 */

class SubmitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val final_score = SpriteList.score.toString()
        setContentView(R.layout.activity_main)
        game_over_text.text=getString(R.string.game_over_message,final_score)
        start_game.setOnClickListener {
            val intent = Intent(this, BoardActivity::class.java)
            val name = user_name.text.toString()
            intent.putExtra("Username", name)
            intent.putExtra("scores",final_score)
            startActivity(intent)
        }
    }
    //Menu Functions
    override fun onCreateOptionsMenu(menu: Menu):Boolean{
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem):Boolean{
        if(item.itemId == R.id.new_game){
            showDialogue(1)
        }
        else if(item.itemId==R.id.leader_board){
            showDialogue(2)
        }
        return true
    }

    fun showDialogue(choice: Int){
        val dialogTitle="CONFIRM"
        var dialogMessage = ""
        if(choice==1) {
            dialogMessage = "Start/Restart the game?"
        }
        if(choice==2) {
            dialogMessage = "Directly go to leader board without saving your score? Click 'yes' to confirm your action"
        }
        val dialogBuilder= AlertDialog.Builder(this)
        dialogBuilder
            .setTitle(dialogTitle)
            .setMessage(dialogMessage)
            .setPositiveButton("YES"){dialog, which ->
                if (choice==1) {
                    Toast.makeText(this, "New Game", Toast.LENGTH_LONG).show()
                    val game_intent = Intent(this, MainActivity::class.java)
                    SpriteList.score=0
                    startActivity(game_intent)
                }
                else{
                    Toast.makeText(this,"Leaderboard", Toast.LENGTH_LONG).show()
                    val lead_intent = Intent(this, BoardActivity::class.java)
                    startActivity(lead_intent)
                }
            }
            .setNegativeButton("No"){dialog, which ->
                Toast.makeText(this, "Stay", Toast.LENGTH_SHORT).show()
            }
            .create()
            .show()
    }
}