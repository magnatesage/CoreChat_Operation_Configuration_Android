package com.magnates.operationConfig.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.LinearLayout
import com.magnates.operationConfig.R
import com.magnates.operationConfig.customviews.colorpicker.AmbilWarnaDialog
import com.magnates.operationConfig.databinding.ActivityIconsConfigBinding
import com.magnates.operationConfig.utils.Extensions.showToast
import com.magnates.operationConfig.utils.Utils

class IconsConfigActivity : BaseActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivityIconsConfigBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIconsConfigBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    /**
     * This method is used to initialization process of activity
     */
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

    /**
     * This method is used to set the values to views
     */
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

        if (!Utils.dynamicUIModel?.icons?.manager?.iconColor.isNullOrBlank()){
            Utils.changeBg(
                binding.selectManagerIconColorLayout.displayColorView,
                Utils.dynamicUIModel?.icons?.manager?.iconColor!!
            )
        }

        if (!Utils.dynamicUIModel?.icons?.supervisor?.iconColor.isNullOrBlank()){
            Utils.changeBg(
                binding.selectSupervisorIconColorLayout.displayColorView,
                Utils.dynamicUIModel?.icons?.supervisor?.iconColor!!
            )
        }

        if (!Utils.dynamicUIModel?.icons?.agent?.iconColor.isNullOrBlank()){
            Utils.changeBg(
                binding.selectAgentIconColorLayout.displayColorView,
                Utils.dynamicUIModel?.icons?.agent?.iconColor!!
            )
        }

        if (!Utils.dynamicUIModel?.icons?.greenFlag?.iconColor.isNullOrBlank()){
            Utils.changeBg(
                binding.selectGreenFlagIconColorLayout.displayColorView,
                Utils.dynamicUIModel?.icons?.greenFlag?.iconColor!!
            )
        }

        if (!Utils.dynamicUIModel?.icons?.umberFlag?.iconColor.isNullOrBlank()){
            Utils.changeBg(
                binding.selectUmberFlagIconColorLayout.displayColorView,
                Utils.dynamicUIModel?.icons?.umberFlag?.iconColor!!
            )
        }

        if (!Utils.dynamicUIModel?.icons?.redFlag?.iconColor.isNullOrBlank()){
            Utils.changeBg(
                binding.selectRedFlagIconColorLayout.displayColorView,
                Utils.dynamicUIModel?.icons?.redFlag?.iconColor!!
            )
        }

        if (!Utils.dynamicUIModel?.icons?.online?.iconColor.isNullOrBlank()){
            Utils.changeBg(
                binding.selectOnlineIconColorLayout.displayColorView,
                Utils.dynamicUIModel?.icons?.online?.iconColor!!
            )
        }

        if (!Utils.dynamicUIModel?.icons?.offline?.iconColor.isNullOrBlank()){
            Utils.changeBg(
                binding.selectOfflineIconColorLayout.displayColorView,
                Utils.dynamicUIModel?.icons?.offline?.iconColor!!
            )
        }

        if (!Utils.dynamicUIModel?.icons?.upcoming?.iconColor.isNullOrBlank()){
            Utils.changeBg(
                binding.selectUpcomingIconColorLayout.displayColorView,
                Utils.dynamicUIModel?.icons?.upcoming?.iconColor!!
            )
        }

        if (!Utils.dynamicUIModel?.icons?.pause?.iconColor.isNullOrBlank()){
            Utils.changeBg(
                binding.selectPauseIconColorLayout.displayColorView,
                Utils.dynamicUIModel?.icons?.pause?.iconColor!!
            )
        }
    }

    /**
     * This method is called when user clicks on view
     */
    override fun onClick(view: View) {
        when (view) {
            binding.selectManagerIconColorLayout.llSelectColor -> {
                callColorPickerDialog(
                    Utils.getColorFromView(binding.selectManagerIconColorLayout.displayColorView),
                    binding.selectManagerIconColorLayout.llSelectColor
                )
            }

            binding.selectSupervisorIconColorLayout.llSelectColor -> {
                callColorPickerDialog(
                    Utils.getColorFromView(binding.selectSupervisorIconColorLayout.displayColorView),
                    binding.selectSupervisorIconColorLayout.llSelectColor
                )
            }

            binding.selectAgentIconColorLayout.llSelectColor -> {
                callColorPickerDialog(
                    Utils.getColorFromView(binding.selectAgentIconColorLayout.displayColorView),
                    binding.selectAgentIconColorLayout.llSelectColor
                )
            }

            binding.selectGreenFlagIconColorLayout.llSelectColor -> {
                callColorPickerDialog(
                    Utils.getColorFromView(binding.selectGreenFlagIconColorLayout.displayColorView),
                    binding.selectGreenFlagIconColorLayout.llSelectColor
                )
            }

            binding.selectUmberFlagIconColorLayout.llSelectColor -> {
                callColorPickerDialog(
                    Utils.getColorFromView(binding.selectUmberFlagIconColorLayout.displayColorView),
                    binding.selectUmberFlagIconColorLayout.llSelectColor
                )
            }

            binding.selectRedFlagIconColorLayout.llSelectColor -> {
                callColorPickerDialog(
                    Utils.getColorFromView(binding.selectRedFlagIconColorLayout.displayColorView),
                    binding.selectRedFlagIconColorLayout.llSelectColor
                )
            }

            binding.selectOnlineIconColorLayout.llSelectColor -> {
                callColorPickerDialog(
                    Utils.getColorFromView(binding.selectOnlineIconColorLayout.displayColorView),
                    binding.selectOnlineIconColorLayout.llSelectColor
                )
            }

            binding.selectOfflineIconColorLayout.llSelectColor -> {
                callColorPickerDialog(
                    Utils.getColorFromView(binding.selectOfflineIconColorLayout.displayColorView),
                    binding.selectOfflineIconColorLayout.llSelectColor
                )
            }

            binding.selectUpcomingIconColorLayout.llSelectColor -> {
                callColorPickerDialog(
                    Utils.getColorFromView(binding.selectUpcomingIconColorLayout.displayColorView),
                    binding.selectUpcomingIconColorLayout.llSelectColor
                )
            }

            binding.selectPauseIconColorLayout.llSelectColor -> {
                callColorPickerDialog(
                    Utils.getColorFromView(binding.selectPauseIconColorLayout.displayColorView),
                    binding.selectPauseIconColorLayout.llSelectColor
                )
            }

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
                    Utils.dynamicUIModel?.icons?.manager?.iconColor.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_icon_color))
                    }
                    Utils.dynamicUIModel?.icons?.supervisor?.iconColor.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_icon_color))
                    }
                    Utils.dynamicUIModel?.icons?.agent?.iconColor.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_icon_color))
                    }
                    Utils.dynamicUIModel?.icons?.greenFlag?.iconColor.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_icon_color))
                    }
                    Utils.dynamicUIModel?.icons?.umberFlag?.iconColor.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_icon_color))
                    }
                    Utils.dynamicUIModel?.icons?.redFlag?.iconColor.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_icon_color))
                    }
                    Utils.dynamicUIModel?.icons?.online?.iconColor.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_icon_color))
                    }
                    Utils.dynamicUIModel?.icons?.offline?.iconColor.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_icon_color))
                    }
                    Utils.dynamicUIModel?.icons?.upcoming?.iconColor.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_icon_color))
                    }
                    Utils.dynamicUIModel?.icons?.pause?.iconColor.isNullOrEmpty() -> {
                        showToast(resources.getString(R.string.error_message_select_icon_color))
                    }
                    else -> {
                        if (Utils.dynamicUIModel?.icons?.manager?.iconColor.isNullOrBlank()){
                            Utils.dynamicUIModel?.icons?.manager?.iconColor = String.format(
                                "#%08x",
                                Utils.getColorFromView(binding.selectManagerIconColorLayout.displayColorView)
                            )
                        }

                        if (Utils.dynamicUIModel?.icons?.supervisor?.iconColor.isNullOrBlank()){
                            Utils.dynamicUIModel?.icons?.supervisor?.iconColor = String.format(
                                "#%08x",
                                Utils.getColorFromView(binding.selectSupervisorIconColorLayout.displayColorView)
                            )
                        }

                        if (Utils.dynamicUIModel?.icons?.agent?.iconColor.isNullOrBlank()){
                            Utils.dynamicUIModel?.icons?.agent?.iconColor = String.format(
                                "#%08x",
                                Utils.getColorFromView(binding.selectAgentIconColorLayout.displayColorView)
                            )
                        }

                        if (Utils.dynamicUIModel?.icons?.greenFlag?.iconColor.isNullOrBlank()){
                            Utils.dynamicUIModel?.icons?.greenFlag?.iconColor = String.format(
                                "#%08x",
                                Utils.getColorFromView(binding.selectGreenFlagIconColorLayout.displayColorView)
                            )
                        }

                        if (Utils.dynamicUIModel?.icons?.umberFlag?.iconColor.isNullOrBlank()){
                            Utils.dynamicUIModel?.icons?.umberFlag?.iconColor = String.format(
                                "#%08x",
                                Utils.getColorFromView(binding.selectUmberFlagIconColorLayout.displayColorView)
                            )
                        }

                        if (Utils.dynamicUIModel?.icons?.redFlag?.iconColor.isNullOrBlank()){
                            Utils.dynamicUIModel?.icons?.redFlag?.iconColor = String.format(
                                "#%08x",
                                Utils.getColorFromView(binding.selectRedFlagIconColorLayout.displayColorView)
                            )
                        }

                        if (Utils.dynamicUIModel?.icons?.online?.iconColor.isNullOrBlank()){
                            Utils.dynamicUIModel?.icons?.online?.iconColor = String.format(
                                "#%08x",
                                Utils.getColorFromView(binding.selectOnlineIconColorLayout.displayColorView)
                            )
                        }

                        if (Utils.dynamicUIModel?.icons?.offline?.iconColor.isNullOrBlank()){
                            Utils.dynamicUIModel?.icons?.offline?.iconColor = String.format(
                                "#%08x",
                                Utils.getColorFromView(binding.selectOfflineIconColorLayout.displayColorView)
                            )
                        }

                        if (Utils.dynamicUIModel?.icons?.upcoming?.iconColor.isNullOrBlank()){
                            Utils.dynamicUIModel?.icons?.upcoming?.iconColor = String.format(
                                "#%08x",
                                Utils.getColorFromView(binding.selectUpcomingIconColorLayout.displayColorView)
                            )
                        }

                        if (Utils.dynamicUIModel?.icons?.pause?.iconColor.isNullOrBlank()){
                            Utils.dynamicUIModel?.icons?.pause?.iconColor = String.format(
                                "#%08x",
                                Utils.getColorFromView(binding.selectPauseIconColorLayout.displayColorView)
                            )
                        }

                        startActivity(Intent(this, BottomMenuConfigActivity::class.java))
                    }
                }
            }
        }
    }

    /**
     * This method is used when user select item in spinner
     */
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

    /**
     * This method is called when user nothing select anything in spinner
     */
    override fun onNothingSelected(p0: AdapterView<*>?) {}

    /**
     * This method is called to open color picker dialog
     */
    private fun callColorPickerDialog(viewColor: Int, linearLayout: LinearLayout) {
        val colorPickerDialog =
            AmbilWarnaDialog(this, viewColor, true, object : AmbilWarnaDialog.OnAmbilWarnaListener {
                override fun onCancel(dialog: AmbilWarnaDialog?) {
                }

                override fun onOk(dialog: AmbilWarnaDialog?, mColor: Int) {
                    setSelectedColor(mColor, linearLayout)
                }
            })
        colorPickerDialog.show()
    }

    /**
     * This method is called to set the selected color
     */
    private fun setSelectedColor(color: Int, linearLayout: LinearLayout) {
        val selectedColor = String.format("#%08x", color)

        when (linearLayout) {
            binding.selectManagerIconColorLayout.llSelectColor -> {
                Utils.changeBg(
                    binding.selectManagerIconColorLayout.displayColorView,
                    selectedColor
                )
                Utils.dynamicUIModel?.icons?.manager?.iconColor = selectedColor
            }

            binding.selectSupervisorIconColorLayout.llSelectColor -> {
                Utils.changeBg(
                    binding.selectSupervisorIconColorLayout.displayColorView,
                    selectedColor
                )
                Utils.dynamicUIModel?.icons?.supervisor?.iconColor = selectedColor
            }

            binding.selectAgentIconColorLayout.llSelectColor -> {
                Utils.changeBg(
                    binding.selectAgentIconColorLayout.displayColorView,
                    selectedColor
                )
                Utils.dynamicUIModel?.icons?.agent?.iconColor = selectedColor
            }

            binding.selectGreenFlagIconColorLayout.llSelectColor -> {
                Utils.changeBg(
                    binding.selectGreenFlagIconColorLayout.displayColorView,
                    selectedColor
                )
                Utils.dynamicUIModel?.icons?.greenFlag?.iconColor = selectedColor
            }

            binding.selectUmberFlagIconColorLayout.llSelectColor -> {
                Utils.changeBg(
                    binding.selectUmberFlagIconColorLayout.displayColorView,
                    selectedColor
                )
                Utils.dynamicUIModel?.icons?.umberFlag?.iconColor = selectedColor
            }

            binding.selectRedFlagIconColorLayout.llSelectColor -> {
                Utils.changeBg(
                    binding.selectRedFlagIconColorLayout.displayColorView,
                    selectedColor
                )
                Utils.dynamicUIModel?.icons?.redFlag?.iconColor = selectedColor
            }

            binding.selectOnlineIconColorLayout.llSelectColor -> {
                Utils.changeBg(
                    binding.selectOnlineIconColorLayout.displayColorView,
                    selectedColor
                )
                Utils.dynamicUIModel?.icons?.online?.iconColor = selectedColor
            }

            binding.selectOfflineIconColorLayout.llSelectColor -> {
                Utils.changeBg(
                    binding.selectOfflineIconColorLayout.displayColorView,
                    selectedColor
                )
                Utils.dynamicUIModel?.icons?.offline?.iconColor = selectedColor
            }

            binding.selectUpcomingIconColorLayout.llSelectColor -> {
                Utils.changeBg(
                    binding.selectUpcomingIconColorLayout.displayColorView,
                    selectedColor
                )
                Utils.dynamicUIModel?.icons?.upcoming?.iconColor = selectedColor
            }

            binding.selectPauseIconColorLayout.llSelectColor -> {
                Utils.changeBg(
                    binding.selectPauseIconColorLayout.displayColorView,
                    selectedColor
                )
                Utils.dynamicUIModel?.icons?.pause?.iconColor = selectedColor
            }
        }
    }
}