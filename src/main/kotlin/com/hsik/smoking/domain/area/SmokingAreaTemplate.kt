package com.hsik.smoking.domain.area

import org.bson.Document
import org.springframework.data.mongodb.core.BulkOperations
import org.springframework.data.mongodb.core.FindAndReplaceOptions
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.convert.MongoConverter
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class SmokingAreaTemplate(
    private val mongoTemplate: MongoTemplate,
    private val mongoConverter: MongoConverter,
) {
    fun upsertAll(smokingAreas: List<SmokingArea>) {
        val operations =
            mongoTemplate.bulkOps(
                BulkOperations.BulkMode.UNORDERED,
                SmokingArea::class.java,
            )

        val options = FindAndReplaceOptions.options()
        smokingAreas.forEach {
            val query =
                Query().apply {
                    addCriteria(Criteria.where(SmokingArea::referenceId.name).`is`(it.referenceId))
                }

            val document =
                Document().apply {
                    mongoConverter.write(it, this)
                }

            operations.replaceOne(query, document, options.upsert())
        }

        operations.execute()
    }
}
