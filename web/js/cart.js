function deleteCart(id) {
    if (confirm("Are you sure to delete cart with id: " + id)) {
        window.location = "deleteCart?id=" + id;
    }
}