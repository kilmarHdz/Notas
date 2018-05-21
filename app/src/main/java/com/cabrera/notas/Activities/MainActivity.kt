package com.cabrera.notas.Activities

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.cabrera.notas.Fragments.AddFragment
import com.cabrera.notas.Fragments.NotasFragment
import com.cabrera.notas.Fragments.VerNotasFragment
import com.cabrera.notas.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var DrawLayouy: DrawerLayout? = null
    var NaView: NavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DrawLayouy = findViewById(R.id.drawer_layout)
        NaView = findViewById(R.id.NavView)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_menu)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        //Dejar un Fragment como primera vista
        changeFrag(AddFragment(), NaView!!.menu.getItem(0))

        NaView!!.setNavigationItemSelectedListener { item ->

            var gestorFrag : Boolean = false
            var fragment: Fragment? = null
            when(item.itemId){
                R.id.menu_newUser ->{
                    fragment = AddFragment()
                    gestorFrag =true
                }
                R.id.menu_notes ->{
                    fragment = NotasFragment()
                    gestorFrag =true
                }
                R.id.menu_notes_show ->{
                    fragment = VerNotasFragment()
                    gestorFrag =true
                }
                R.id.menu_borrar ->{
                    Toast.makeText(this,"Borrar", Toast.LENGTH_LONG).show()
                }
                R.id.menu_help ->{
                    Toast.makeText(this,"Ayuda", Toast.LENGTH_LONG).show()
                }
            }
            if (gestorFrag){
                changeFrag(fragment,item)
                drawer_layout!!.closeDrawers()
            }
            true
        }

    }

    fun changeFrag(fragment: Fragment?, item: MenuItem){
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit()
        //seleccion de menu
        item.isChecked = true
        supportActionBar!!.title = item.title
    }


    //Habilitar que con el boton se despliegue el menu
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item!!.itemId){
            android.R.id.home -> {
                DrawLayouy!!.openDrawer(GravityCompat.START)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

}
