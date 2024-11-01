package com.hsik.smoking.domain.client.government

interface OpenDataPortalClient {
    fun findAllSmokingAreaInDongdaemunGu(): List<OpenDataPortalResources.SmokingArea.DongdaemunGu>
}
