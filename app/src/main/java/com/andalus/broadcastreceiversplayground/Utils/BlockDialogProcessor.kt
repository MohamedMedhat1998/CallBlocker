package com.andalus.broadcastreceiversplayground.Utils

import android.app.Application
import android.content.Intent
import com.andalus.broadcastreceiversplayground.DatabaseOperations.QueryAllOperation
import com.andalus.broadcastreceiversplayground.Objects.BlockedContact
import com.andalus.broadcastreceiversplayground.Utils.Interfaces.Processor

class BlockDialogProcessor(private val query: QueryAllOperation<BlockedContact>) : Processor {

    override fun startProcessing(application: Application, incomingNumber: String?) {

        val intent = Intent(Constants.BLOCK_DIALOG_ACTION)
        var isInDatabase = false
        intent.putExtra(Constants.INCOMING_NUMBER_KEY, incomingNumber)

        query.getAll {

            if (it != null) {
                for (item in it) {

                    if (item.contactNumber.contains(incomingNumber.toString(), true) ||
                        incomingNumber?.contains(item.contactNumber, true) == true
                    ) {
                        isInDatabase = true
                        break
                    }

                }
            }

            if (!isInDatabase) {
                application.startActivity(intent)
            }

        }

    }
}