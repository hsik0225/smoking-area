package com.hsik.smoking.common

import com.hsik.smoking.domain.town.area.SmokingArea

fun SmokingArea.relativeOrThrow(townId: String): SmokingArea {
    if (this.townId.toString() != townId) {
        throw ResourceNotFoundException("SmokingArea 의 ${this.townId}와 입력한 ${townId}가 일치하지 않습니다.")
    }
    return this
}
