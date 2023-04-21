package agency.firefly.app_no_1

import agency.firefly.app_no_1.databinding.ActivityMainBinding
import agency.firefly.app_no_1.databinding.CellNumberBinding
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class MainActivity : AppCompatActivity(), OnNumberCellClickListener {

    // pretvara XML u klasu
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //pristupamo XML kao objektu
        setContentView(binding.root)

        //adapter ne zna kako će te ćelije biti organizirane na ekrane. Za to služi recyclerView. Adapter zna pojedinu ćeliju
        //recyclerView isto ne zna sam, zato mu predajemo layout managera
/*
        val adapter = NumberAdapter(object : OnNumberCellClickListener {
            override fun onClick(number: Int) {
                Toast.makeText(this@MainActivity, "Cell clicked: $number", Toast.LENGTH_SHORT)
                    .show()
            }
        })
 */
        val adapter = NumberAdapter(this)
        //dodali smo adapter recylerView-u
        //kotlin spaja adapter get i set metode u jedno
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)


    }

    // ovako se radi kad imaš jednu jednostavnu metodu. Kada ih imamo više, rade se zasebni objekti kao gore u komentaru
    override fun onClick(number: Int) {
        val intent = Intent(this, NumberActivity::class.java)
        intent.putExtra("key", number)
       startActivity(intent)
    }


}

//ako se stavi generički RV.ViewHolder, onda možemo koristiti više viewholdera koje imamo
class NumberAdapter(val listener: OnNumberCellClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    //ova metoda se zove kada se prikaže ekran i prikazuje onoliko ćelija koliko stane
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CellNumberBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = NumberViewHolder(binding)
        return viewHolder
    }

    //kada se pomakne ekran, ova metoda se zove da se već postojeće ćelije napune novim podacima
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //castali smo holder kao numberviewholder. Možemo jer smo definirali iznad da je viewHolder NumberViewHolder
        (holder as NumberViewHolder).bind(position, listener)
    }

    override fun getItemCount(): Int {
        return 20
    }
}


// kako se prikazuje neka ćelija; ponaša se kao zaseban objekt na ekranu
// view holder drži logiku vezanu samo za UI, za tu specifičnu ćeliju
class NumberViewHolder(val binding: CellNumberBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(position: Int, listener: OnNumberCellClickListener) {
        binding.root.text = "Number: $position"

        binding.root.setOnClickListener {
            listener.onClick(position)
        }
    }
}

interface OnNumberCellClickListener {
    fun onClick(number: Int)
}



// object ==== class nesto
// tools u XML-u je samo placeholder, ignorira se kad se app pokrene
// veličina teksta je u SP
// klasa koja sadržava sve o uređaju (jezik, orjentacija, dark/light mode...)
/*

        private val TAG = "MainActivity"

        binding.button.isEnabled = false
        //Ima više metoda (on, before, after)
        binding.inputLine.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d(TAG, "onTextChanged: $s")
                binding.button.isEnabled = !(s == null || s.isEmpty())
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        // pristupamo objektu button
        binding.button.setOnClickListener {
            val textToEncrypt = binding.inputLine.text.toString()
            val key = binding.keyLine.text.toString()
            val encryptedText = encryptMessage(textToEncrypt, key.toInt())
            binding.outputLine.text = encryptedText
        }

        private fun encryptMessage(message: String, keyEnc: Int): String {

        val firstArray = message.toCharArray().toMutableList()
        val secondArray = mutableListOf<Char>()

        if (binding.radioButton1.isChecked) {
            for (i in firstArray) {
                val newChar = (i.code + keyEnc)
                secondArray.add(newChar.toChar())
            }
        } else if (binding.radioButton2.isChecked) {
            for (i in firstArray) {
                if (!i.isLetter()) {
                    secondArray.add(i)
                    continue
                }
                var newChar: Int = 0

                if (i.isUpperCase()) {
                    newChar = ((i.code + keyEnc - 65) % 26 + 65)
                } else if (i.isLowerCase()) {
                    newChar = ((i.code + keyEnc - 97) % 26 + 97)
                }
                secondArray.add(newChar.toChar())
            }
        }

        return secondArray.joinToString("")
    }
 */