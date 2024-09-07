function deleteOrder(id) {
    if (confirm("Are you sure to delete order with id: " + id)) {
        window.location = "deleteOrder?id=" + id;
    }
}