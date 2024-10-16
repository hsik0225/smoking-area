package com.hsik.smoking.domain.client

interface OpenDataPortalClient {
    fun findAllSmokingAreaInDongdaemunGu(): List<OpenDataPortalResources.SmokingArea.DongdaemunGu>
}
