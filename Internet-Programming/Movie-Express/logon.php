<?php
ini_set('display_startup_errors', 1); // Report compiler (syntax) errors
ini_set('display_errors', 1); // Report run-time errors
error_reporting(E_ALL); // Do not hide any errors

processPageRequest(); // Call the processPageRequest() function

/**
 * Test whether the user entered valid login credentials
 * @param $username
 * @param $password
 */
function authenticateUser($username, $password)
{
    // Read the credentials.db file and store the values in an array.
    $cred_file = fopen("./data/credentials.db", "r") or die("Unable to open credentials file!");

    // Create an array
    $credArray = array();

    // Loop through the credentials file converting values into an array
    while (!feof($cred_file)) {
        $line = fgets($cred_file);
        $credArray[] = explode(',', $line);
    }
    fclose($cred_file); // Close credentials file

    // Loop through the array, authenticating the user
    $arrLength = count($credArray);
    for ($x = 0; $x < $arrLength; $x++) {
        if ($username == $credArray[$x][0] && $password == $credArray[$x][1]) {
            session_start();
            $_SESSION["display_name"] = $credArray[$x][2];
            $_SESSION["email"] = $credArray[$x][3];
            $_SESSION["isEmail"] = false;
            header('Location: ./index.php');
            break;
        }
    }

    // If no username/password match, display error message
    displayLoginForm("Invalid Username and/or Password");
}

/**
 * Displays the login form and sets a message is there is one.
 * @param string $message
 */
function displayLoginForm($message = "")
{
    require_once('./templates/logon_form.html');
}

/**
 * Clears all session values, then test whether or not POST data was
 * passed to the page.
 */
function processPageRequest()
{
    session_unset(); // Clear all session variable
    if ($_SERVER['REQUEST_METHOD'] == 'POST') {
        authenticateUser($_POST["username"], $_POST["pwd"]);
    } else {
        displayLoginForm();
    }
}
