<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Debtor information</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/css/bootstrap.min.css"
              integrity="sha384-r4NyP46KrjDleawBgD5tp8Y7UzmLA05oM1iAEQ17CSuDqnUK2+k9luXQOfXJCJ4I" crossorigin="anonymous">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                display: flex;
                align-items: center;
                justify-content: center;
                min-height: 100vh;
                background-color: #83b3e3;
            }

            #debt-info {
                position: relative;
                width: 500px;
                background-color: #ffffff;
                padding: 20px;
                border-radius: 10px;
                border:1px solid rgba(17, 12, 46, 0.15);
                box-shadow: rgba(17, 12, 46, 0.15) 0px 48px 100px 0px;
            }

            .form-title {
                text-align:center;
                font-size:30px;
                font-weight:600;
                margin:20px 0px 30px;
                color:#111;
            }

            #debt-info h1 {
                font-size: 24px;
                margin-bottom: 10px;
                text-align: center;
            }

            #debt-info label {
                display: block;
                margin-top: 10px;
                font-size: 16px;
            }

            #debt-info input:not([readonly]),
            #debt-info textarea,
            #debt-info button {
                width: calc(100% - 16px);
                padding: 8px;
                margin-top: 5px;
                box-sizing: border-box;
            }

            .total-debt input {
                pointer-events: none;
            }

            .fa-plus{
                content: "\F067";
            }


            .exit-button {
                position: absolute;
                top: 10px;
                right: 10px;
                cursor: pointer;
                max-width: 30px;
            }

        </style>
    </head>

    <body>
        <form id="debt-form" action="updateDebtor" method="post"enctype="multipart/form-data" novalidate onsubmit="return validateForm()">
            <div id="debt-info">
                <div>
                    <a href="diary?id=${sessionScope.userLogin.id}"><img class="exit-button" type="button" src="assets/img/reject.png" class="refresh-icon" ></a> 
                </div>
                <div class="form-title">
                    Debtor information
                </div>
                <input type="hidden" name="idDebtor" value="${debtor.id}">
                <div class="row mt-2">
                    <div class="col-md-12">
                        <label class="labels">Avatar</label>
                        <img src="${debtor.avatar}" alt="Avatar" style="max-width: 20%; height: auto;  border-radius: 50%" id="avatar" onchange="previewImage(event)">
                        <input type="file" class="form-control" name="avatar"  >
                        <input type="hidden" name="avatar" value="${debtor.avatar}" >
                        <img id="avatar-preview" src="#" alt="Preview" style="max-width: 100px; max-height: 100px; margin-top: 10px; display: none; border-radius: 50%">
                    </div>
                    <div class="col-md-12">
                        <label class="labels">Full Name</label>
                        <input type="text" class="form-control" name="fullName" value="${debtor.fullName}" placeholder="Enter full name" required>
                        <div id="fullName-error" class="text-danger"></div>
                    </div>
                    <div class="col-md-12">
                        <label class="labels">Phone Number</label>
                        <input type="text" class="form-control" name="phoneNumber" placeholder="Enter phone number" value="${debtor.phoneNumber}" required>
                        <div id="phoneNumber-error" class="text-danger"></div>
                    </div>
                    <div class="col-md-12">
                        <label class="labels">Email</label>
                        <input type="text" class="form-control" name="email" placeholder="Enter email" value="${debtor.email}" required>
                        <div id="email-error" class="text-danger"></div>
                    </div>
                    <div class="col-md-12">
                        <label class="labels">Address</label>
                        <input type="address" class="form-control" name="address" placeholder="Enter address" value="${debtor.address}" required>
                        <div id="address-error" class="text-danger"></div>
                    </div>
                    <div class="col-md-12 total-debt">
                        <label class="labels">Total Debt</label>
                        <input type="text" class="form-control" value="${debtor.amount}" readonly style="color: ${debtor.amount < 0 ? 'red' : 'black'};">
                    </div>
                </div>
                <div class="mt-3 text-center">
                    <button class="btn btn-primary profile-button add-button" type="button" style="width: 200px;" id="update"> <i class="fas fa-sync-alt"></i> UPDATE</button>
                </div>
            </div>
        </form>
    </body>

    <!-- Modal Confirmation -->
    <div class="modal fade" id="confirmationModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="modalLabel">Confirm</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    Do you want to change this debtor  ? 
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" id="confirmSubmit">Confirm</button>
                </div>
            </div>
        </div>
    </div>
    <!-- Success Modal -->
    <div class="modal fade" id="successModal" tabindex="-1" role="dialog" aria-labelledby="successModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="successModalLabel">Success</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    Change debtor successfully!!!
                </div>
                <div class="modal-footer">
                    <!-- Trong modal th�nh c�ng c?a b?n -->
                    <button type="button" id="closeSuccessModalButton" class="btn btn-default">Close</button>

                </div>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script src="js/updateDebtor.js"></script>      


</html>