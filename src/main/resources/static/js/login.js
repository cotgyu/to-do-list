const deleteBoard = function (boardId){

    if(confirm('정말로 삭제하시겠습니까?')){
        const topicName = $('#board'+boardId).val();

        const data = {
            id:boardId,
            boardName: topicName,
            delFlag: 'Y'
        }

        $.ajax({
            type: 'PUT',
            url: '/api/board/'+boardId,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)

        }).done(function () {
            location.reload();
        }).fail(function (error){
            alert(JSON.stringify(error));
        });

    }

}

const updateBoardMode = function (boardId){

    $('#boardView'+boardId).css('display', 'none');
    $('#editBoardNameInput'+boardId).css('display', '');
    $('#editBoardNameButton'+boardId).css('display', 'none');
    $('#saveBoardNameButton'+boardId).css('display', '');


}

const updateBoard = function (boardId){
    const boardName = $('#editBoardNameInput'+boardId).val();

    if(boardName == ''){
        location.reload();
        return;
    }

    const data = {
        id:boardId,
        boardName: boardName,
        delFlag: 'N'
    }

    $.ajax({
        type: 'PUT',
        url: '/api/board/'+boardId,
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(data)

    }).done(function () {
        location.reload();
    }).fail(function (error){

        if(error.responseJSON.resultMessage == 'fail'){
            alert(error.responseJSON.result);
        }

    });
}

const addBoardMode = function (){

    $('#addBoardButton').css('display','none');
    $('#addBoardNameInput').css('display','');
    $('#saveBoardButton').css('display','');

}

const saveBoard = function (){

    const boardName = $('#addBoardNameInput').val();

    const data = {
        boardName: boardName,
        delFlag: 'N'
    }

    $.ajax({
        type: 'POST',
        url: '/api/board',
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(data)

    }).done(function () {
        location.reload();
    }).fail(function (error){
        alert(JSON.stringify(error));
    });

}