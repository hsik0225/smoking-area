package com.hsik.smoking.domain.town.client

interface OpenDataPortalClient {
    fun findAllSmokingAreaInDongdaemunGu(): List<OpenDataPortalResources.SmokingArea.DongdaemunGu>
}
