    $(document).ready(function () {
    $(".btn-delete").on("click", function (e) {
        e.preventDefault();  // Empêche la navigation

        let personId = $(this).attr("data-id");
        let personFirstname = $(this).attr("data-firstname");

        $("#confirmText").html("Do you want to delete <strong>" + personFirstname + "</strong>?");
        $("#yesBtn").attr("data-id", personId);  // Stocke l'ID de la personne
        $("#confirmModal").modal("show");  // Affiche la modale
    });

    $("#yesBtn").on("click", function () {
    let personId = $(this).attr("data-id");

    $.ajax({
    url: "/persons/delete/" + personId,
    type: "DELETE",
    success: function (response) {
    $("#confirmModal").modal("hide");  // Ferme la modale
    $("a[data-id='" + personId + "']").closest("tr").fadeOut();  // Supprime la ligne du tableau
},
    error: function () {
    alert("Error deleting person");
}
});
});
});

