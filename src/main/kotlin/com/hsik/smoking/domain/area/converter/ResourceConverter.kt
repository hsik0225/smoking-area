package com.hsik.smoking.domain.area.converter

import com.hsik.smoking.domain.area.SmokingArea

interface ResourceConverter {
    fun convert(): List<SmokingArea>
}
