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
        binding.spinnerLive.onItemSelectedListener = this
        binding.spinnerTickets.onItemSelectedListener = this
        binding.spinnerBot.onItemSelectedListener = this
        binding.spinnerTimer.onItemSelectedListener = this
        binding.spinnerMessage.onItemSelectedListener = this
        binding.spinnerDisconnect.onItemSelectedListener = this
        binding.spinnerSchedule.onItemSelectedListener = this
        binding.spinnerAllTime.onItemSelectedListener = this
        binding.spinnerUser.onItemSelectedListener = this
        binding.spinnerInfo.onItemSelectedListener = this
        binding.spinnerNotes.onItemSelectedListener = this
        binding.spinnerRemove.onItemSelectedListener = this
        binding.spinnerTrainingMode.onItemSelectedListener = this
        binding.spinnerEndSession.onItemSelectedListener = this
        binding.spinnerWhisper.onItemSelectedListener = this
        binding.spinnerInteract.onItemSelectedListener = this
        binding.spinnerBotStart.onItemSelectedListener = this
        binding.spinnerUserLocation.onItemSelectedListener = this
        binding.spinnerManager.onItemSelectedListener = this
        binding.spinnerSupervisor.onItemSelectedListener = this
        binding.spinnerAgent.onItemSelectedListener = this
        binding.spinnerGreenFlag.onItemSelectedListener = this
        binding.spinnerUmberFlag.onItemSelectedListener = this
        binding.spinnerRedFlag.onItemSelectedListener = this
        binding.spinnerOnline.onItemSelectedListener = this
        binding.spinnerOffline.onItemSelectedListener = this
        binding.spinnerUpcoming.onItemSelectedListener = this
        binding.spinnerPause.onItemSelectedListener = this

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

        Utils.dynamicUIModel?.icons?.live?.iconValue?.let {
            Utils.getIndex(
                Utils.getStringArrayFromXML(this, R.array.dropdown_live_icon_array),
                it
            )
        }?.let { binding.spinnerLive.setSelection(it) }

        Utils.dynamicUIModel?.icons?.tickets?.iconValue?.let {
            Utils.getIndex(
                Utils.getStringArrayFromXML(this, R.array.dropdown_tickets_icon_array),
                it
            )
        }?.let { binding.spinnerTickets.setSelection(it) }

        Utils.dynamicUIModel?.icons?.bot?.iconValue?.let {
            Utils.getIndex(
                Utils.getStringArrayFromXML(this, R.array.dropdown_bot_icon_array),
                it
            )
        }?.let { binding.spinnerBot.setSelection(it) }

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

        Utils.dynamicUIModel?.icons?.disconnect?.iconValue?.let {
            Utils.getIndex(
                Utils.getStringArrayFromXML(this, R.array.dropdown_disconnect_icon_array),
                it
            )
        }?.let { binding.spinnerDisconnect.setSelection(it) }

        Utils.dynamicUIModel?.icons?.schedule?.iconValue?.let {
            Utils.getIndex(
                Utils.getStringArrayFromXML(this, R.array.dropdown_schedule_icon_array),
                it
            )
        }?.let { binding.spinnerSchedule.setSelection(it) }

        Utils.dynamicUIModel?.icons?.allTime?.iconValue?.let {
            Utils.getIndex(
                Utils.getStringArrayFromXML(this, R.array.dropdown_all_time_icon_array),
                it
            )
        }?.let { binding.spinnerAllTime.setSelection(it) }

        Utils.dynamicUIModel?.icons?.user?.iconValue?.let {
            Utils.getIndex(
                Utils.getStringArrayFromXML(this, R.array.dropdown_user_icon_array),
                it
            )
        }?.let { binding.spinnerUser.setSelection(it) }

        Utils.dynamicUIModel?.icons?.info?.iconValue?.let {
            Utils.getIndex(
                Utils.getStringArrayFromXML(this, R.array.dropdown_info_icon_array),
                it
            )
        }?.let { binding.spinnerInfo.setSelection(it) }

        Utils.dynamicUIModel?.icons?.notes?.iconValue?.let {
            Utils.getIndex(
                Utils.getStringArrayFromXML(this, R.array.dropdown_notes_icon_array),
                it
            )
        }?.let { binding.spinnerNotes.setSelection(it) }

        Utils.dynamicUIModel?.icons?.remove?.iconValue?.let {
            Utils.getIndex(
                Utils.getStringArrayFromXML(this, R.array.dropdown_remove_icon_array),
                it
            )
        }?.let { binding.spinnerRemove.setSelection(it) }

        Utils.dynamicUIModel?.icons?.trainingMode?.iconValue?.let {
            Utils.getIndex(
                Utils.getStringArrayFromXML(this, R.array.dropdown_training_mode_icon_array),
                it
            )
        }?.let { binding.spinnerTrainingMode.setSelection(it) }

        Utils.dynamicUIModel?.icons?.endSession?.iconValue?.let {
            Utils.getIndex(
                Utils.getStringArrayFromXML(this, R.array.dropdown_end_session_icon_array),
                it
            )
        }?.let { binding.spinnerEndSession.setSelection(it) }

        Utils.dynamicUIModel?.icons?.whisper?.iconValue?.let {
            Utils.getIndex(
                Utils.getStringArrayFromXML(this, R.array.dropdown_whisper_icon_array),
                it
            )
        }?.let { binding.spinnerWhisper.setSelection(it) }

        Utils.dynamicUIModel?.icons?.interact?.iconValue?.let {
            Utils.getIndex(
                Utils.getStringArrayFromXML(this, R.array.dropdown_interact_icon_array),
                it
            )
        }?.let { binding.spinnerInteract.setSelection(it) }

        Utils.dynamicUIModel?.icons?.botStart?.iconValue?.let {
            Utils.getIndex(
                Utils.getStringArrayFromXML(this, R.array.dropdown_bot_start_icon_array),
                it
            )
        }?.let { binding.spinnerBotStart.setSelection(it) }

        Utils.dynamicUIModel?.icons?.userLocation?.iconValue?.let {
            Utils.getIndex(
                Utils.getStringArrayFromXML(this, R.array.dropdown_user_location_icon_array),
                it
            )
        }?.let { binding.spinnerUserLocation.setSelection(it) }

        Utils.dynamicUIModel?.icons?.manager?.iconValue?.let {
            Utils.getIndex(
                Utils.getStringArrayFromXML(this, R.array.dropdown_manager_icon_array),
                it
            )
        }?.let { binding.spinnerManager.setSelection(it) }

        Utils.dynamicUIModel?.icons?.supervisor?.iconValue?.let {
            Utils.getIndex(
                Utils.getStringArrayFromXML(this, R.array.dropdown_supervisor_icon_array),
                it
            )
        }?.let { binding.spinnerSupervisor.setSelection(it) }

        Utils.dynamicUIModel?.icons?.agent?.iconValue?.let {
            Utils.getIndex(
                Utils.getStringArrayFromXML(this, R.array.dropdown_agent_icon_array),
                it
            )
        }?.let { binding.spinnerAgent.setSelection(it) }

        Utils.dynamicUIModel?.icons?.greenFlag?.iconValue?.let {
            Utils.getIndex(
                Utils.getStringArrayFromXML(this, R.array.dropdown_green_flag_icon_array),
                it
            )
        }?.let { binding.spinnerGreenFlag.setSelection(it) }

        Utils.dynamicUIModel?.icons?.umberFlag?.iconValue?.let {
            Utils.getIndex(
                Utils.getStringArrayFromXML(this, R.array.dropdown_umber_flag_icon_array),
                it
            )
        }?.let { binding.spinnerUmberFlag.setSelection(it) }

        Utils.dynamicUIModel?.icons?.redFlag?.iconValue?.let {
            Utils.getIndex(
                Utils.getStringArrayFromXML(this, R.array.dropdown_red_flag_icon_array),
                it
            )
        }?.let { binding.spinnerRedFlag.setSelection(it) }

        Utils.dynamicUIModel?.icons?.online?.iconValue?.let {
            Utils.getIndex(
                Utils.getStringArrayFromXML(this, R.array.dropdown_online_icon_array),
                it
            )
        }?.let { binding.spinnerOnline.setSelection(it) }

        Utils.dynamicUIModel?.icons?.offline?.iconValue?.let {
            Utils.getIndex(
                Utils.getStringArrayFromXML(this, R.array.dropdown_offline_icon_array),
                it
            )
        }?.let { binding.spinnerOffline.setSelection(it) }

        Utils.dynamicUIModel?.icons?.upcoming?.iconValue?.let {
            Utils.getIndex(
                Utils.getStringArrayFromXML(this, R.array.dropdown_upcoming_icon_array),
                it
            )
        }?.let { binding.spinnerUpcoming.setSelection(it) }

        Utils.dynamicUIModel?.icons?.pause?.iconValue?.let {
            Utils.getIndex(
                Utils.getStringArrayFromXML(this, R.array.dropdown_pause_icon_array),
                it
            )
        }?.let { binding.spinnerPause.setSelection(it) }
    }

    override fun onClick(view: View) {
        when (view) {
            binding.btnBack -> onBackPressed()

            binding.btnNext -> {
                when {
                    Utils.dynamicUIModel?.icons?.profile?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_profile_icon))
                    }
                    Utils.dynamicUIModel?.icons?.live?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_live_icon))
                    }
                    Utils.dynamicUIModel?.icons?.tickets?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_tickets_icon))
                    }
                    Utils.dynamicUIModel?.icons?.bot?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_bot_icon))
                    }
                    Utils.dynamicUIModel?.icons?.timer?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_timer_icon))
                    }
                    Utils.dynamicUIModel?.icons?.message?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_message_icon))
                    }
                    Utils.dynamicUIModel?.icons?.notes?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_notes_icon))
                    }

                    Utils.dynamicUIModel?.icons?.disconnect?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_disconnect_icon))
                    }
                    Utils.dynamicUIModel?.icons?.schedule?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_schedule_icon))
                    }
                    Utils.dynamicUIModel?.icons?.allTime?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_all_time_icon))
                    }
                    Utils.dynamicUIModel?.icons?.user?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_user_icon))
                    }
                    Utils.dynamicUIModel?.icons?.info?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_info_icon))
                    }
                    Utils.dynamicUIModel?.icons?.remove?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_remove_icon))
                    }
                    Utils.dynamicUIModel?.icons?.trainingMode?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_training_mode_icon))
                    }
                    Utils.dynamicUIModel?.icons?.endSession?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_end_session_icon))
                    }
                    Utils.dynamicUIModel?.icons?.whisper?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_whisper_icon))
                    }
                    Utils.dynamicUIModel?.icons?.interact?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_interact_icon))
                    }
                    Utils.dynamicUIModel?.icons?.botStart?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_bot_start_icon))
                    }
                    Utils.dynamicUIModel?.icons?.userLocation?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_user_location_icon))
                    }
                    Utils.dynamicUIModel?.icons?.manager?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_manager_icon))
                    }
                    Utils.dynamicUIModel?.icons?.supervisor?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_supervisor_icon))
                    }
                    Utils.dynamicUIModel?.icons?.agent?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_agent_icon))
                    }
                    Utils.dynamicUIModel?.icons?.greenFlag?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_green_flag_icon))
                    }
                    Utils.dynamicUIModel?.icons?.umberFlag?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_umber_flag_icon))
                    }
                    Utils.dynamicUIModel?.icons?.redFlag?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_red_flag_icon))
                    }
                    Utils.dynamicUIModel?.icons?.online?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_online_icon))
                    }
                    Utils.dynamicUIModel?.icons?.offline?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_offline_icon))
                    }
                    Utils.dynamicUIModel?.icons?.upcoming?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_upcoming_icon))
                    }
                    Utils.dynamicUIModel?.icons?.pause?.iconValue.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_pause_icon))
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
            binding.spinnerNotes -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.icons?.notes?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.icons?.notes?.iconValue = ""
                }
            }
            binding.spinnerTickets -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.icons?.tickets?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.icons?.tickets?.iconValue = ""
                }
            }
            binding.spinnerBot -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.icons?.bot?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.icons?.bot?.iconValue = ""
                }
            }
            binding.spinnerDisconnect -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.icons?.disconnect?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.icons?.disconnect?.iconValue = ""
                }
            }
            binding.spinnerSchedule -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.icons?.schedule?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.icons?.schedule?.iconValue = ""
                }
            }
            binding.spinnerAllTime -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.icons?.allTime?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.icons?.allTime?.iconValue = ""
                }
            }
            binding.spinnerUser -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.icons?.user?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.icons?.user?.iconValue = ""
                }
            }
            binding.spinnerInfo -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.icons?.info?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.icons?.info?.iconValue = ""
                }
            }
            binding.spinnerRemove -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.icons?.remove?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.icons?.remove?.iconValue = ""
                }
            }
            binding.spinnerTrainingMode -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.icons?.trainingMode?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.icons?.trainingMode?.iconValue = ""
                }
            }
            binding.spinnerEndSession -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.icons?.endSession?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.icons?.endSession?.iconValue = ""
                }
            }
            binding.spinnerWhisper -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.icons?.whisper?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.icons?.whisper?.iconValue = ""
                }
            }
            binding.spinnerInteract -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.icons?.interact?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.icons?.interact?.iconValue = ""
                }
            }
            binding.spinnerBotStart -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.icons?.botStart?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.icons?.botStart?.iconValue = ""
                }
            }
            binding.spinnerUserLocation -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.icons?.userLocation?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.icons?.userLocation?.iconValue = ""
                }
            }
            binding.spinnerManager -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.icons?.manager?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.icons?.manager?.iconValue = ""
                }
            }
            binding.spinnerSupervisor -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.icons?.supervisor?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.icons?.supervisor?.iconValue = ""
                }
            }
            binding.spinnerAgent -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.icons?.agent?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.icons?.agent?.iconValue = ""
                }
            }
            binding.spinnerGreenFlag -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.icons?.greenFlag?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.icons?.greenFlag?.iconValue = ""
                }
            }
            binding.spinnerUmberFlag -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.icons?.umberFlag?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.icons?.umberFlag?.iconValue = ""
                }
            }
            binding.spinnerRedFlag -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.icons?.redFlag?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.icons?.redFlag?.iconValue = ""
                }
            }
            binding.spinnerOnline -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.icons?.online?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.icons?.online?.iconValue = ""
                }
            }
            binding.spinnerOffline -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.icons?.offline?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.icons?.offline?.iconValue = ""
                }
            }
            binding.spinnerUpcoming -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.icons?.upcoming?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.icons?.upcoming?.iconValue = ""
                }
            }
            binding.spinnerPause -> {
                if (position != 0) {
                    Utils.dynamicUIModel?.icons?.pause?.iconValue =
                        Utils.getOriginalIconValue(parent.getItemAtPosition(position).toString())
                } else {
                    Utils.dynamicUIModel?.icons?.pause?.iconValue = ""
                }
            }
        }

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
}