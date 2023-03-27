package com.gmail.eamosse.imdb

import android.os.Bundle
import android.view.OnReceiveContentListener
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

/**
 * Activité principale de l'application
 * Ce sera la seule activité de l'application
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        initNavController()
    }

    /**
     * Méthode utilitaire permettant de gérer la navigation
     */
    private fun initNavController() {
        //Instance de la bottom navigation
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        setSupportActionBar(this.toolbar)

        //Navigation controlleur, utilisée pour géter la navigation (ex. affichage de fragment)
        val navController = findNavController(R.id.nav_host_fragment)
        //Charger les éléments principaux de la bottom bar
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_favorites, R.id.navigation_notifications, R.id.navigation_profile
            )
        )
        //Indiquer les éléments principaux de la bottom bar
        setupActionBarWithNavController(navController, appBarConfiguration)
        //Finalement, on lie la bottom bar et la nav controller
        navView.setupWithNavController(navController)

        //Gestion de la toolbar
        navController.addOnDestinationChangedListener { _: NavController, destination: NavDestination, _: Bundle? ->
            if (destination.id == R.id.navigation_home) {
                toolbar.visibility = View.GONE
            } else {
                toolbar.visibility = View.VISIBLE
            }
        }
    }


}
