function addMovie(movieID) {
    window.location.replace("./index.php?action=add&movie_id=" + movieID);
    return true;
}

function confirmCheckout() {
    if (confirm('Are you sure that you want to checkout from myMovies Xpress?')) {
        window.location.replace("./index.php?action=checkout");
        return true;
    } else {
        return false;
    }
}

function confirmLogout() {
    if (confirm('Are you sure that you want to logout of myMovies Xpress?')) {
        window.location.replace("./logon.php?action=logoff");
        return true;
    } else {
        return false;
    }
}

function confirmRemove(title, movieID) {
    if (confirm('Are you sure that you want to remove ' + title + ' from the cart?')) {
        window.location.replace("./index.php?action=remove&movie_id=" + movieID);
        return true;
    } else {
        return false;
    }
}

