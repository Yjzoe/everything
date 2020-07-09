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
        var data = {
            category : $('#category').val(),
            title: $('#title').val(),
            writer: $('#writer').val(),
            content: $('#content').val(),
            gpa : $('input[name=gpa]:checked').val(),
            oneSentence : $('#oneSentence').val(),
            img : null
        }
        $.ajax({
            type: 'POST',
            url: '/api/v1/reviews',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('글이 등록되었습니다.')
            window.location.href='/';
        }).fail(function (error) {
            alert(JSON.stringify(error))
        });
    },
    update: function () {
        var data = {
            category : $('#category').val(),
            title: $('#title').val(),
            content: $('#content').val(),
            gpa : $('input[name=gpa]:checked').val(),
            oneSentence : $('#oneSentence').val(),
            img : null
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
    }
}

main.init()