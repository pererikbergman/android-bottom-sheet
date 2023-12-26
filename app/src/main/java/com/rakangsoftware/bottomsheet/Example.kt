package com.rakangsoftware.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditableTextLabelScreen() {
    var currentData by remember { mutableStateOf("Initial Text") }
    var showConfirmationDialog by remember { mutableStateOf(false) }
    var tempText by remember { mutableStateOf(currentData) }

    var isEditing by rememberSaveable {
        mutableStateOf(false)
    }

    val sheetState = rememberModalBottomSheetState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Current Data:",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = currentData,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { isEditing = true },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Edit Data", color = MaterialTheme.colorScheme.onPrimaryContainer)
        }
    }

    if (isEditing) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = { isEditing = false }
        ) {
            EditBottomSheetContent(
                tempText = tempText,
                onTextChange = { tempText = it },
                bottomSheetState = sheetState,
                onSave = {
                    currentData = tempText
                    isEditing = false
                    showConfirmationDialog = true
                }
            )
        }
    }

    if (showConfirmationDialog) {
        AlertDialog(
            onDismissRequest = { showConfirmationDialog = false },
            confirmButton = {
                TextButton(onClick = { showConfirmationDialog = false }) {
                    Text("OK")
                }
            },
            text = { Text("Data Updated") }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditBottomSheetContent(
    tempText: String,
    onTextChange: (String) -> Unit,
    bottomSheetState: SheetState,
    onSave: () -> Unit
) {
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(bottomSheetState.isVisible) {
        if (bottomSheetState.isVisible) {
            focusRequester.requestFocus()
        }
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        TextField(
            value = tempText,
            onValueChange = onTextChange,
            label = { Text("Edit Text") },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = onSave,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save")
        }
    }
}
