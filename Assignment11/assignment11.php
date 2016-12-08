<?php
session_start();
logout();

// Function that ends the session and logs the user out
function logout() {
    if( isset($_REQUEST['logout']) ) {
        // Destroy the session
        session_unset();
        session_destroy();
        session_start();
    }
}

// Function that gets user info and stores it in session
function getUserInfo($username, $password) {
    $file = fopen("assignment11-account-info.txt", "r") or exit("Unable to open user file");
    while(!feof($file)) {
        $line = trim(fgets($file));
        if (strlen($line) > 0) {
            $arr = explode(";", $line);
            if($username == $arr[0] && $password == $arr[1]) {
                $_SESSION['firstName'] = $arr[2];
                $_SESSION['lastName'] = $arr[3];
                $_SESSION['color'] = $arr[4];
                $_SESSION['title'] = $arr[5];
                $_SESSION['imageLink'] = $arr[6];
            }
        }
    }
    fclose($file);
}

// Function that validates a given username and password against the users file
function validateUserInfo($username, $password) {
    $file = fopen("assignment11-account-info.txt", "r") or exit("Unable to open user file");
    $index = 0;
    while(!feof($file)) {
        $line = trim(fgets($file));
        if (strlen($line) > 0) {
            $arr = explode(";", $line);
            if($username == $arr[0] && $password == $arr[1]) {
                fclose($file);
                return true;
            }
        }
    }
    fclose($file);
    return false;
}

// Function that updates a user's info based on the form on their page
function updateUserInfo() {
    // Checks that info has been updated
    if( isset($_REQUEST['firstName']) ) {
        // Reads file into array
        $file = fopen("assignment11-account-info.txt", "r+") or exit("Unable to open user file");
        $counter = 0;
        while(!feof($file)) {
            $line = trim(fgets($file));
            if( strlen($line) > 0 ) {
                $fileArr[$counter] = $line;
                $counter++;
            }
        }
        // Searches array for relevant user entry and replaces it
        for( $x = 0; $x < $counter; $x++) {
            $lineArr = explode(";", $fileArr[$x]);

            if( $lineArr[2] == $_SESSION['firstName'] && $lineArr[3] == $_SESSION['lastName'] ) {
                $lineArr[2] = $_REQUEST['firstName'];
                $lineArr[3] = $_REQUEST['lastName'];
                $lineArr[4] = $_REQUEST['color'];
                $lineArr[5] = $_REQUEST['title'];
                $lineArr[6] = $_REQUEST['imageLink'];

                $fileArr[$x] = implode(";", $lineArr);
            }
        }
        // Rewrites info to file
        file_put_contents("assignment11-account-info.txt", "");
        for( $x = 0; $x < $counter; $x++ ) {
            fwrite($file, $fileArr[$x]."\n");
        }

        // Updates session settings
        $_SESSION['firstName'] = $_REQUEST['firstName'];
        $_SESSION['lastName'] = $_REQUEST['lastName'];
        $_SESSION['color'] = $_REQUEST['color'];
        $_SESSION['title'] = $_REQUEST['title'];
        $_SESSION['imageLink'] = $_REQUEST['imageLink'];
    }
}

// === Start of website code ===

updateUserInfo();

// If user enter info that matches the file, set the session info
if( isset($_REQUEST['username']) && isset($_REQUEST['password']) ) {
    if( validateUserInfo($_REQUEST['username'], $_REQUEST['password']) ) {
        getUserInfo($_REQUEST['username'], $_REQUEST['password']);
    }
    else echo "Error: Invalid username/password.";
}


// Page to be displayed if session info is set
if( isset($_SESSION['firstName']) ) { ?>
    <html>
    <body style="background-color: <?php echo $_SESSION['color']; ?>">
        <!-- Display user's title and image -->
        <title> <?php echo $_SESSION['title']; ?> </title>
        <h1> <?php echo $_SESSION['title']; ?> </h1>
        <img src="<?php echo $_SESSION['imageLink']; ?>" alt="User's Image" style="width:15%">
        <br />
        <!-- Logout button to clear session -->
        <form action="assignment11.php" method="get" >
            <input type="hidden" name="logout" value="true" />
            <input type="submit" value="Logout" />
        </form>
        <br />
        <!-- Form to change user settings -->
        <form action="assignment11.php" method="get" >
            First name: <input type="text" name="firstName" value="<?php echo $_SESSION['firstName']; ?>"> <br />
            Last name: <input type="text" name="lastName" value="<?php echo $_SESSION['lastName']; ?>"> <br />
            Background color: <input type="text" name="color" value="<?php echo $_SESSION['color']; ?>"> <br />
            Title: <input type="text" name="title" value="<?php echo $_SESSION['title']; ?>"> <br />
            Image: <input type="text" name="imageLink" value="<?php echo $_SESSION['imageLink']; ?>"> <br />
            <input type="submit" />
        </form>
    </body>
    </html>
<?php
    }

// Login form
else {
    ?>
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
