const delAccount = document.getElementById('delAccount')
const delPosts = document.getElementById('delPosts')

$(delAccount).click(() => {
  if (confirm("Are you sure that you want to DELETE account?")) {
    alert("Account Deleted Successfully")
    return true;
  } else {
    alert("Phew, that was close!")
    return false;
  }
})

$(delPosts).click(() => {
  if (confirm("Are you sure that you want to delete ALL your posts?")) {
    alert("Posts Deleted Successfully")
    return true;
  } else {
    alert("Phew, that was close!")
    return false;
  }
})
