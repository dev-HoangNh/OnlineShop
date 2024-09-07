function minusQuantity() {
  var quantityInput = document.getElementById("quantity");
  var currentQuantity = parseInt(quantityInput.value);
  
  if (currentQuantity > 1) {
    quantityInput.value = currentQuantity - 1;
  }
}

function addQuantity() {
  var quantityInput = document.getElementById("quantity");
  var currentQuantity = parseInt(quantityInput.value);
  
  quantityInput.value = currentQuantity + 1;
}

