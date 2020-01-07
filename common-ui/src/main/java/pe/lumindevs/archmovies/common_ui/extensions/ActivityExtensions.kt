package pe.lumindevs.archmovies.common_ui.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import pe.lumindevs.archmovies.common_ui.R

/** apply title to the toolbal simply*/
fun AppCompatActivity.simpleToolbarWithHome(toolbar: Toolbar, _title: String = ""){
    setSupportActionBar(toolbar)
    supportActionBar?.run{
        setDisplayHomeAsUpEnabled(true)
        setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        title = _title
    }
}