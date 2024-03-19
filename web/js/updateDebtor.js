
document.getElementById('updatedebtor').addEventListener('click', function (event) {
    event.preventDefault();
    let isValid = true;
    const fullName = document.getElementById('fullName').value;
    if (!fullName.trim()) {
        document.getElementById('fullName-error').textContent = 'Please enter a full name.';
        isValid = false;
    } else {
        document.getElementById('fullName-error').textContent = '';
    }

    if (isValid) {
        $('#confirmModal').modal('show');
    }
});

document.getElementById('confirmUpdate').addEventListener('click', function () {
    $('#confirmModal').modal('hide');
    $('#successModal').modal('show');
});

$('#successModal').on('hidden.bs.modal', function () {
    document.getElementById('debt-form').submit();
});

window.addEventListener('pageshow', function (event) {
    if (event.persisted) {
        window.location.reload();
    } else {
        document.forms[0].reset();
    }
});

