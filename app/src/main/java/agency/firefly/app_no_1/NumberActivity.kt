package agency.firefly.app_no_1

import agency.firefly.app_no_1.databinding.ActivityMainBinding
import agency.firefly.app_no_1.databinding.ActivityNumberBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

//appcompact activity nasljeđuje abstractu klasu = mora imati konstruktor
class NumberActivity : AppCompatActivity(){

    private val binding: ActivityNumberBinding by lazy {
        ActivityNumberBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.number.text = intent.getIntExtra("key", 0).toString()
    }

}