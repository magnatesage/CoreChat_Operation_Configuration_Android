package com.example.corechatoperationconfigapp.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.example.corechatoperationconfigapp.R
import com.example.corechatoperationconfigapp.databinding.ActivityIconsConfigBinding
import com.example.corechatoperationconfigapp.utils.Extensions.showToast
import com.example.corechatoperationconfigapp.utils.Utils

class IconsConfigActivity : BaseActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivityIconsConfigBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIconsConfigBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun init() {
        binding.headerLayout.tvHeader.text = getString(R.string.icons_configuration)
        binding.spinnerProfile.onItemSelectedListener = this
        binding.spinnerNotificationSelected.onItemSelectedListener = this
        binding.spinnerNotificationUnselected.onItemSelectedListener = this
        binding.spinnerFavouritesSelected.onItemSelectedListener = this
        binding.spinnerFavouritesUnselected.onItemSelectedListener = this
        binding.spinnerNotes.onItemSelectedListener = this
        binding.spinnerLive.onItemSelectedListener = this
        binding.spinnerTimer.onItemSelectedListener = this
        binding.spinnerMessage.onItemSelectedListener = this

        binding.root.post {
            setValuesToViews()
        }
    }

    private fun setValuesToViews() {
        Utils.dynamicUIModel?.icons?.profile?.iconValue?.let {
            Utils.getIndex(
                Utils.getStringArrayFromXML(this, R.array.dropdown_profile_icon_array),
                it
            )
        }?.let { binding.spinnerProfile.setSelection(it) }

        Utils.dynamicUIModel?.icons?.notificationSelected?.iconValue?.let {
            Utils.getIndex(
                Utils.getStringArrayFromXML(
                    this,
                    R.array.dropdown_notification_selected_icon_array
                ),
                it
            )
        }?.let { binding.spinnerNotificationSelected.setSelection(it) }

        Utils.dynamicUIModel?.icons?.notificationUnselected?.iconValue?.let {
            Utils.getIndex(
                Utils.getStringArrayFromXML(
                    this,
                    R.array.dropdown_notification_unselected_icon_array
                ),
                it
            )
        }?.let { binding.spinnerNotificationUnselected.setSelection(it) }

        Utils.dynamicUIModel?.icons?.favouritesSelected?.iconValue?.let {
            Utils.getIndex(
                Utils.getStringArrayFromXML(this, R.array.dropdown_favourites_selected_icon_array),
                it
            )
        }?.let { binding.spinnerFavouritesSelected.setSelection(it) }

        Utils.dynamicUIModel?.icons?.favouritesUnselected?.iconValue?.let {
            Utils.getIndex(
                Utils.getStringArrayFromXML(
                    this,
                    R.array.dropdown_favourites_unselected_icon_array
                ),
                it
            )
        }?.let { binding.spinnerFavouritesUnselected.setSelection(it) }

        Utils.dynamicUIModel?.icons?.notes?.iconValue?.let {
            Utils.getIndex(
                Utils.getStringArrayFromXML(this, R.array.dropdown_notes_icon_array),
                it
            )
        }?.let { binding.spinnerNotes.setSelection(it) }

        Utils.dynamicUIModel?.icons?.live?.iconValue?.let {
            Utils.getIndex(
                Utils.getStringArrayFromXML(this, R.array.dropdown_live_icon_array),
                it
            )
        }?.let { binding.spinnerLive.setSelection(it) }

        Utils.dynamicUIModel?.icons?.timer?.iconValue?.let {
            Utils.getIndex(
                Utils.getStringArrayFromXML(this, R.array.dropdown_timer_icon_array),
                it
            )
        }?.let { binding.spinnerTimer.setSelection(it) }

        Utils.dynamicUIModel?.icons?.message?.iconValue?.let {
            Utils.getIndex(
                Utils.getStringArrayFromXML(this, R.array.dropdown_message_icon_array),
                it
            )
        }?.let { binding.spinnerMessage.setSelection(it) }

    }

    override fun onClick(view: View) {
        when (view) {
            binding.btnBack -> onBackPressed()

            binding.btnNext -> {
                when {
                    Utils.dynamicUIModel?.icons?.profile?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_profile_icon))
                    }
                    Utils.dynamicUIModel?.icons?.notificationSelected?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_notification_selected_icon))
                    }
                    Utils.dynamicUIModel?.icons?.notificationUnselected?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_notification_unselected_icon))
                    }
                    Utils.dynamicUIModel?.icons?.favouritesSelected?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_favourites_selected_icon))
                    }
                    Utils.dynamicUIModel?.icons?.favouritesUnselected?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_favourites_unselected_icon))
                    }
                    Utils.dynamicUIModel?.icons?.notes?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_notes_icon))
                    }
                    Utils.dynamicUIModel?.icons?.live?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_live_icon))
                    }
                    Utils.dynamicUIModel?.icons?.timer?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_timer_icon))
                    }
                    Utils.dynamicUIModel?.icons?.message?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_message_icon))
                    }
                    else -> {
                        startActivity(Intent(this, BottomMenuConfigActivity::class.java))
                    }
                }
            }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent) {
            binding.spinnerProfile -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.icons?.profile?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.icons?.profile?.iconValue = ""
                }
            }
            binding.spinnerNotificationSelected -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.icons?.notificationSelected?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.icons?.notificationSelected?.iconValue = ""
                }
            }
            binding.spinnerNotificationUnselected -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.icons?.notificationUnselected?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.icons?.notificationUnselected?.iconValue = ""
                }
            }
            binding.spinnerFavouritesSelected -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.icons?.favouritesSelected?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.icons?.favouritesSelected?.iconValue = ""
                }
            }
            binding.spinnerFavouritesUnselected -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.icons?.favouritesUnselected?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.icons?.favouritesUnselected?.iconValue = ""
                }
            }
            binding.spinnerNotes -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.icons?.notes?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.icons?.notes?.iconValue = ""
                }
            }
            binding.spinnerLive -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.icons?.live?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.icons?.live?.iconValue = ""
                }
            }
            binding.spinnerTimer -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.icons?.timer?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.icons?.timer?.iconValue = ""
                }
            }
            binding.spinnerMessage -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.icons?.message?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.icons?.message?.iconValue = ""
                }
            }
        }

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
}