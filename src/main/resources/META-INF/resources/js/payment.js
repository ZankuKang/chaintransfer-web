function handlePayment() {
    const resultDiv = document.getElementById('result');
    resultDiv.innerHTML = "<p class='text-yellow-400'>⏳ Processing...</p>";

    fetch('/payment', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            description: 'Increase file size limit'
        })
    })
        .then(res => res.text())
        .then(html => resultDiv.innerHTML = html)
        .catch(() => resultDiv.innerHTML =
            "<p class='text-red-400'>❌ Payment failed.</p>");
}