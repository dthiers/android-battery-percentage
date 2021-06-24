package com.example.battery_percentage

import android.app.ActionBar
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.BatteryManager
import android.os.Bundle
import android.text.Layout
import android.text.PrecomputedText
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.view.children
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import com.example.battery_percentage.databinding.FragmentFirstBinding
import kotlinx.coroutines.withTimeout

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private var _context: Context? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    /**
     * // values for "status" field in the ACTION_BATTERY_CHANGED Intent
    public static final int BATTERY_STATUS_UNKNOWN = Constants.BATTERY_STATUS_UNKNOWN;
    public static final int BATTERY_STATUS_CHARGING = Constants.BATTERY_STATUS_CHARGING;
    public static final int BATTERY_STATUS_DISCHARGING = Constants.BATTERY_STATUS_DISCHARGING;
    public static final int BATTERY_STATUS_NOT_CHARGING = Constants.BATTERY_STATUS_NOT_CHARGING;
    public static final int BATTERY_STATUS_FULL = Constants.BATTERY_STATUS_FULL;

    // values for "health" field in the ACTION_BATTERY_CHANGED Intent
    public static final int BATTERY_HEALTH_UNKNOWN = Constants.BATTERY_HEALTH_UNKNOWN;
    public static final int BATTERY_HEALTH_GOOD = Constants.BATTERY_HEALTH_GOOD;
    public static final int BATTERY_HEALTH_OVERHEAT = Constants.BATTERY_HEALTH_OVERHEAT;
    public static final int BATTERY_HEALTH_DEAD = Constants.BATTERY_HEALTH_DEAD;
    public static final int BATTERY_HEALTH_OVER_VOLTAGE = Constants.BATTERY_HEALTH_OVER_VOLTAGE;
    public static final int BATTERY_HEALTH_UNSPECIFIED_FAILURE = Constants.BATTERY_HEALTH_UNSPECIFIED_FAILURE;
    public static final int
     */

    private val _batteryMap = mapOf<String, Int>(
        "BATTERY_STATUS_CHARGING" to BatteryManager.BATTERY_STATUS_CHARGING,
        "BATTERY_STATUS_DISCHARGING" to BatteryManager.BATTERY_STATUS_DISCHARGING,
        "BATTERY_STATUS_FULL" to BatteryManager.BATTERY_STATUS_FULL,
        "BATTERY_STATUS_NOT CHARGING" to BatteryManager.BATTERY_STATUS_NOT_CHARGING,
        "BATTERY_STATUS_UNKNOWN" to BatteryManager.BATTERY_STATUS_UNKNOWN,
        "BATTERY_HEALTH_COLD" to BatteryManager.BATTERY_HEALTH_COLD,
        "BATTERY_HEALTH_DEAD" to BatteryManager.BATTERY_HEALTH_DEAD,
        "BATTERY_HEALTH_GOOD" to BatteryManager.BATTERY_HEALTH_GOOD,
        "BATTERY_HEALTH_OVERHEAT" to BatteryManager.BATTERY_HEALTH_OVERHEAT,
        "BATTERY_HEALTH_OVER_VOLTAGE" to BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE,
        "BATTERY_HEALTH_UNKNOWN" to BatteryManager.BATTERY_HEALTH_UNKNOWN,
        "BATTERY_HEALTH_UNSPECIFIED_FAILARE" to BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE,
        "BATTERY_PROPERTY_STATUS" to BatteryManager.BATTERY_PROPERTY_STATUS,
        "BATTERY_PROPERTY_CURRENT_NOW" to BatteryManager.BATTERY_PROPERTY_CURRENT_NOW,
        "BATTERY_PROPERTY_CAPACITY" to BatteryManager.BATTERY_PROPERTY_CAPACITY,
        "BATTERY_PROPERTY_CHARGE_COUNTER" to BatteryManager.BATTERY_PROPERTY_CHARGE_COUNTER,
        "BATTERY_PROPERTY_CURRENT_AVERAGE" to BatteryManager.BATTERY_PROPERTY_CURRENT_AVERAGE,
        "BATTERY_PROPERTY_ENERGY_COUNTER" to BatteryManager.BATTERY_PROPERTY_ENERGY_COUNTER,
    )
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _context = activity?.applicationContext

        loadBatteryStatsInLinearView()

        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            loadBatteryStatsInLinearView()
        }
    }

    private fun loadBatteryStatsInLinearView(){
        var batteryManager: BatteryManager = this._context?.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
        var linearLayout: LinearLayout? = _binding?.linearLayout

        linearLayout?.removeAllViews()
        _batteryMap.forEach { (k, v) ->
            var text = batteryManager.getIntProperty(v)
//            var text = batteryManager.getLongProperty(v)
            var textView: TextView = TextView(_context).apply {
                this.text = "$k = ${text.toString()}"
                this.textSize = 12F
                this.setTextColor(Color.MAGENTA)
                this.gravity = Gravity.LEFT
                this.layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
            linearLayout?.addView(textView)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}