package io.reyurnible.order.ui.routes.order_confirmation

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.reyurnible.order.ui.screens.OrderConfirmationScreen

@Composable
fun OrderConfirmationRoute(
    orderViewModel: OrderConfirmationViewModel,
    onOrderCompleted: () -> Unit,
) {
    val uiState = orderViewModel.uiState.collectAsStateWithLifecycle()

    OrderConfirmationScreen(
        uiState = uiState.value,
        onOrderButtonClicked = {
            orderViewModel.onOrderButtonClicked(onMoveToComplete = {
                onOrderCompleted()
            })
        }
    )

}