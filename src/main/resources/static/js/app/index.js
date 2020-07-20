var main = {
    init: function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });

        $('#btn-update').on('click',function () {
            _this.update();
        })

        $('#btn-delete').on('click',function () {
            _this.delete();
        })
    },
    save: function () {
        var img = "";
        if ($('#newFile').val().length >= 1) {
            var x = this.imgUpload()
            img = x;
        }

        var data = {
            category : $('#category').val(),
            title: $('#title').val(),
            writer: $('#writer').val(),
            content: $('#content').val(),
            rate : $('input[name=rate]:checked').val(),
            oneSentence : $('#oneSentence').val(),
            img: img
        };
        $.ajax({
            type: 'POST',
            url: '/api/v1/reviews',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('글이 등록되었습니다.');
            window.location.href='/';
        }).fail(function (error) {
            alert(JSON.stringify(error))
        });
    },
    update: function () {
        var oldFile = $('#oldFile').val()
        var img = oldFile
        if ($('#newFile').val().length >= 1) {
            var x = this.imgUpload()
            img = x;
        }

        var data = {
            category : $('#category').val(),
            title: $('#title').val(),
            content: $('#content').val(),
            rate : $('input[name=rate]:checked').val(),
            oneSentence : $('#oneSentence').val(),
            img : img
        }

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/reviews/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=UTF-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('글이 수정되었습니다.')
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error))
        })
    },
    delete: function () {
        var id = $('#id').val();
        $.ajax({
            type: 'DELETE',
            url: '/api/v1/reviews/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=UTF-8'
        }).done(function () {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error))
        });
    },
    imgUpload: function () {
        var res = "";
        var form = new FormData();
        form.append("writer", $('#writer').val());
        form.append("imgFile", $('#newFile')[0].files[0], $('#newFile').val().slice(12));

        var settings = {
            "url": "/image/upload",
            "method": "PUT",
            "timeout": 0,
            "processData": false,
            "mimeType": "multipart/form-data",
            "contentType": false,
            "data": form,
            "async" : false
        };
        $.ajax(settings).done(function (response) {
            res = response.toString();
        });
        return res;
    }
}

main.init()