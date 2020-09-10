package com.example.simplegame

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.leaderboard.*

/*
Ke Zhao
CIS 195 Homework
 */
class BoardActivity : AppCompatActivity() {
    companion object {
        val players: ArrayList<PlayerStat> = ArrayList<PlayerStat>()
        var first_open: Boolean=true
    }
    var new_user: String =""
    var new_score: String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.leaderboard)
        if(first_open){
            initPlayer()
            first_open=false
        }
        try {
            new_user = intent.getStringExtra("Username")
            new_score = intent.getStringExtra("scores")
            addPlayer()
        }catch(e: Exception){
            Log.d("except","Something wrong")
        }
        reset_board.setOnClickListener{
            reset()
        }
        val playerAdapter = ListViewAdapter (this, players)
        players_list_view.adapter = playerAdapter
    }
    fun initPlayer(){
        val sharedPrefs=this.getSharedPreferences("database",Context.MODE_PRIVATE)
        var result_name=sharedPrefs.getString("first",null)
        var result_score=sharedPrefs.getString("first_score",null)
        if(result_name!=null&& result_score!=null){
            val player1 =PlayerStat(result_name,result_score)
            players.add(player1)
        }
        result_name=sharedPrefs.getString("second",null)
        result_score=sharedPrefs.getString("second_score",null)
        if(result_name!=null&& result_score!=null){
            val player2 =PlayerStat(result_name,result_score)
            players.add(player2)
        }
        result_name=sharedPrefs.getString("third",null)
        result_score=sharedPrefs.getString("third_score",null)
        if(result_name!=null&& result_score!=null){
            val player3 =PlayerStat(result_name,result_score)
            players.add(player3)
        }
        result_name=sharedPrefs.getString("fourth",null)
        result_score=sharedPrefs.getString("fourth_score",null)
        if(result_name!=null&& result_score!=null){
            val player4 =PlayerStat(result_name,result_score)
            players.add(player4)
        }
        result_name=sharedPrefs.getString("fifth",null)
        result_score=sharedPrefs.getString("fifth_score",null)
        if(result_name!=null&& result_score!=null){
            val player5 =PlayerStat(result_name,result_score)
            players.add(player5)
        }

    }
    //add new players to the leader board
    fun addPlayer(){
        if (players.size==0){
            players.add(PlayerStat(new_user,new_score))
        }
        else {
            loop@ for (x in 0..(players.size - 1)) {
                if (new_score.toInt() >= players[x].score.toInt()) {
                    players.add(x, PlayerStat(new_user, new_score))
                    break@loop
                }
            }
        }
        if(players.size<=5){
            if (new_score.toInt() < players[players.size-1].score.toInt()){
                players.add(PlayerStat(new_user, new_score))
            }
        }
        else{
            players.removeAt(players.size-1)
        }
        storeSharePreferences(true)//update the sharePreference

    }
    //reset the leader board
    fun reset(){
        val dialogTitle="CONFIRM"
        val dialogMessage = "Confirm to reset the leaderboard"
        val dialogBuilder= AlertDialog.Builder(this)
        dialogBuilder
            .setTitle(dialogTitle)
            .setMessage(dialogMessage)
            .setPositiveButton("YES"){dialog, which ->
                    Toast.makeText(this, "Reset", Toast.LENGTH_SHORT).show()
                    players.clear()
                    storeSharePreferences(false)
                    finish()
                    startActivity(Intent(this, BoardActivity::class.java))
                }
            .setNegativeButton("No"){dialog, which ->
                Toast.makeText(this, "Leaderboard not reset", Toast.LENGTH_SHORT).show()
            }
            .create()
            .show()
    }
    fun storeSharePreferences(check: Boolean){
        val sharedPref=this.getSharedPreferences("database", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        if(check) {
            if (players.size <= 5) {
                if(!first_open) {
                    //editor.clear()
                }
                editor.putString("first", players[0].user_name)
                editor.putString("first_score", players[0].score)
                editor.putString("second", players[1].user_name)
                editor.putString("second_score", players[1].score)
                editor.putString("third", players[2].user_name)
                editor.putString("third_score", players[2].score)
                editor.putString("fourth", players[3].user_name)
                editor.putString("fourth_score", players[3].score)
                editor.putString("fifth", players[4].user_name)
                editor.putString("fifth_score", players[4].score)
            }
        }
        else{
            editor.clear()
        }
        editor.commit()
    }
    //Menu Functions
    override fun onCreateOptionsMenu(menu: Menu):Boolean{
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem):Boolean{
        if(item.itemId == R.id.new_game){
            showDialogue()
        }
        else if(item.itemId==R.id.leader_board){
            Toast.makeText(this, "You are at leader board page", Toast.LENGTH_SHORT).show()
        }
        return true
    }

    fun showDialogue(){
        val dialogTitle="CONFIRM"
        val dialogMessage = "Start/Restart the game?"
        val dialogBuilder= AlertDialog.Builder(this)
        dialogBuilder
            .setTitle(dialogTitle)
            .setMessage(dialogMessage)
            .setPositiveButton("YES"){dialog, which ->
                Toast.makeText(this, "New Game", Toast.LENGTH_LONG).show()
                val game_intent = Intent(this, MainActivity::class.java)
                SpriteList.score=0
                startActivity(game_intent)
            }
            .setNegativeButton("No"){dialog, which ->
                Toast.makeText(this, "Stay", Toast.LENGTH_SHORT).show()
            }
            .create()
            .show()
    }
}
