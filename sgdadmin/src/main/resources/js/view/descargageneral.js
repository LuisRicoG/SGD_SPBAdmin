function getDocument(url, fileName) {
    var req = new XMLHttpRequest();
    req.open("POST", url, true);
    req.responseType = "blob";

    req.onload = function (event) {
        if (req.status !== 200) {
            alert("Ocurrio un error al descargar el archivo\n o no se encuentra disponible");
            return;
        }
        var blob = req.response;
        console.log(blob.size);
        if (window.navigator.msSaveBlob) {
            var blobObject = new Blob([blob]);
            window.navigator.msSaveBlob(blobObject, fileName);
            return;
        }
        var link = document.createElement('a');
        link.href = window.URL.createObjectURL(blob);
        link.download = fileName;
        document.body.appendChild(link);
        link.click();
    };
    req.send();
}

