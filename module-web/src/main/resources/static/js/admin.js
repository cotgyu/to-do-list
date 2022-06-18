const updateUserMode = function (userId) {
    $('#userName' + userId).css("display", "none");
    $('#editUserNameInput' + userId).css("display", "");

    $('#userEmail' + userId).css("display", "none");
    $('#editUserEmailInput' + userId).css("display", "");

    $('#userPicture' + userId).css("display", "none");
    $('#editUserPictureInput' + userId).css("display", "");

    $('#userRole' + userId).css("display", "none");
    $('#editUserRoleInput' + userId).css("display", "");
    const userRole = $('#userRoleValue' + userId).val();
    $('#editUserRoleInput' + userId).val(userRole).prop("selected", true);

    $('#userDelFlag' + userId).css("display", "none");
    $('#editUserDelFlagInput' + userId).css("display", "");
    const userDelFlag = $('#userDelFlagValue' + userId).val();
    $('#editUserDelFlagInput' + userId).val(userDelFlag).prop("selected", true);

    $('#userUpdateModeButton' + userId).css("display", "none");
    $('#userUpdateButton' + userId).css("display", "");
}

const updateUser = function (userId) {

    const userName = $('#editUserNameInput' + userId).val();
    const userEmail = $('#editUserEmailInput' + userId).val();
    const userPicture = $('#editUserPictureInput' + userId).val();

    const userRole = $('#editUserRoleInput' + userId).val();
    const userDelFlag = $('#editUserDelFlagInput' + userId).val();

    const data = {
        name: userName,
        email: userEmail,
        picture: userPicture,
        role: userRole,
        delFlag: userDelFlag
    }

    $.ajax({
        type: 'PUT',
        url: '/api/admin/' + userId,
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(data)

    }).done(function (data) {
        location.reload();
    }).fail(function (error) {
        alert(JSON.stringify(error));
    });
}