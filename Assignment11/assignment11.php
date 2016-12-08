<?php
function getUserInfo() {
        $file = fopen("assignment11-account-info.txt", "r") or exit("Unable to open user file");
        while(!feof($file)) {
            $line = trim(fgets($file));
            if (strlen($line) > 0) {
                $arr = explode(";", $line);
                $_SESSION['firstName'] = $arr[2];
                $_SESSION['lastName'] = $arr[3];
                $_SESSION['color'] = $arr[4];
                $_SESSION['title'] = $arr[5];
                $_SESSION['imageLink'] = $arr[6];
            }
        }
    }

function validateUserInfo($username, $password) {
    $file = fopen("assignment11-account-info.txt", "r") or exit("Unable to open user file");
    $index = 0;
    while(!feof($file)) {
        $line = trim(fgets($file));
        if (strlen($line) > 0) {
            $arr = explode(";", $line);
            if($username == $arr[0] && $password == $arr[1]) {
                return true;
            }
        }
    }
    return false;
}

if( isset($_REQUEST['username']) && isset($_REQUEST['password'])
    && validateUserInfo($_REQUEST['username'], $_REQUEST['password']) ) {
      session_start();
      getUserInfo();
      echo $_SESSION['firstName'];

}

else { ?>
    <html>
        <title>Welcome to Adam Bliss's Assignment 11 PHP page!</title>
        <h1>Welcome to Adam Bliss's Assignment 11 PHP page!</h1>
        <body>
            <p> Existing Users:
            <form action="assignment11.php" method="get">
                Username: <input type="text" name="username" /> <br />
                Password: <input type="text" name="password" /> <br />
                <input type="submit" /> <br />
            </form>
            <p> New Users:
            <form action="assignment11.php" method="get">
                Choose your username: <input type="text" name="newUsername" /> <br />
                Choose your password: <input type="text" name="newPassword" /> <br />
                <input type="submit" /> <br />
            </form>
        </body>
    </html>
<?php
    }
 ?>
