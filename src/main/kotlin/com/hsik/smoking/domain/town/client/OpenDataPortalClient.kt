package com.hsik.smoking.domain.town.client

interface OpenDataPortalClient {
    fun findAllSmokingAreaByDongDaemungu(): List<OpenDataPortalResources.SmokingArea.DongDaeMunGu>
}
