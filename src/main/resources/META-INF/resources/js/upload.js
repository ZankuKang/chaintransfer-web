document.getElementById('fileToUpload').addEventListener('change', function () {
    const file = this.files[0];
    if (!file) return;

    const formData = new FormData();
    formData.append('file', file);

    fetch('/upload', {method: 'POST', body: formData})
        .then(res => res.text())
        .then(html => document.getElementById('result').innerHTML = html)
        .catch(() => document.getElementById('result').innerHTML =
            "<p class='text-red-400'>❌ Something went wrong.</p>");
});