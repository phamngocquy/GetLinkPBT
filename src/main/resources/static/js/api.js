var PARSER_API = {

    make_token: function (url) {
        $.ajax({
            method: 'GET',
            url: 'api/token/maketoken',
            headers: {
                "url": url
            },
            success: function (result) {
                console.log(result);
                if (result.length > 1) {
                    var key = result[0].toString();
                    for (let i = 1; i < result.length; i++) {
                        var encData = result[i].toString();
                        var decrypted = CryptoJS.AES.decrypt(encData, key);
                        console.log(decrypted.toString(CryptoJS.enc.Utf8));
                    }
                }
            }
        })
    }
};