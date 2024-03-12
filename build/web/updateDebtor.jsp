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
        <form id="debt-form" action="updateDebtor" method="post"enctype="multipart/form-data">
            <div id="debt-info">
                <div>
                    <a href="diary?id=${sessionScope.userLogin.id}"><img class="exit-button" type="button" src="assets/img/reject.png" class="refresh-icon" ></a> 
                </div>
                <div class="form-title">
                    Debtor information
                </div>
                <div>${noti}</div>

                <input type="hidden" name="idDebtor" value="${debtor.id}">
                <div class="row mt-2">
                    <div class="col-md-12">
                        <label class="labels">Avatar</label>
                        <img src="${debtor.avatar}" alt="Avatar" style="max-width: 20%; height: auto;  border-radius: 50%">
                        <input type="file" class="form-control" name="avatar"  >
                        <input type="hidden" name="avatar" value="${debtor.avatar}" >
                    </div>
                    <div class="col-md-12">
                        <label class="labels">Full Name</label>
                        <input type="text" class="form-control" name="fullName" value="${debtor.fullName}" placeholder="Enter full name" required>
                    </div>
                    <div class="col-md-12">
                        <label class="labels">Phone Number</label>
                        <input type="text" class="form-control" name="phoneNumber" placeholder="Enter phone number" value="${debtor.phoneNumber}" required>
                    </div>
                    <div class="col-md-12">
                        <label class="labels">Address</label>
                        <input type="address" class="form-control" name="address" placeholder="Enter address" value="${debtor.address}" required>
                    </div>
                    <div class="col-md-12 total-debt">
                        <label class="labels">Total Debt</label>
                        <input type="text" class="form-control" value="${debtor.amount}" readonly>
                    </div>
                </div>
                <div class="mt-3 text-center">
                    <button class="btn btn-primary profile-button add-button" type="submit" style="width: 200px;"> <i class="fas fa-sync-alt"></i> UPDATE</button>
                </div>
            </div>
        </form>
    </body>

</html>