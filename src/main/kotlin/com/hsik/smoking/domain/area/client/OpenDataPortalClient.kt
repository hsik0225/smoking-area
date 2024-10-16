package com.hsik.smoking.domain.area.client

interface OpenDataPortalClient {
    fun findAllSmokingAreaByDongDaemungu(): List<OpenDataPortalResources.SmokingArea.DongDaeMunGu>
}
