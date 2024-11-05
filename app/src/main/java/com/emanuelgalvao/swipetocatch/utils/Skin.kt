package com.emanuelgalvao.swipetocatch.utils

object Skin {

    private var selectedSkin = PlayerSkin.SKIN1

    fun getSelectedSkin() = selectedSkin

    fun setSelectedSkin(skin: PlayerSkin) {
        selectedSkin = skin
    }
}