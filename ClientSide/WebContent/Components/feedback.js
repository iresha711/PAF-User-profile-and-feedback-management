$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	{
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	// Form validation-------------------
	var status = validateItemForm();
	if (status != true)
	{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	
	// If valid------------------------
	//$("#formItem").submit();
	var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
{
	url : "FeedBackAPI",
	type : type,
	data : $("#formFeedBack").serialize(),
	dataType : "text",
	complete : function(response, status)
		{
			onItemSaveComplete(response.responseText, status);
		}
	});
});



// CLIENT-MODEL================================================================
function validateItemForm()
{
	// Email
	if ($("#NIC").val().trim() == "")
	{
		return "Insert Email.";
	}
	
	// Message
	if ($("#Name").val().trim() == "")
	{
		return "Insert Message.";
	}

	
	return true;
	
	function onItemSaveComplete(response, status)
	{
		if (status == "success")
		{
			var resultSet = JSON.parse(response);
			if (resultSet.status.trim() == "success")
			{
				$("#alertSuccess").text("Successfully sent.");
				$("#alertSuccess").show();
				$("#divItemsGrid").html(resultSet.data);
				} else if (resultSet.status.trim() == "error")
		{
				$("#alertError").text(resultSet.data);
				$("#alertError").show();
		}
		} else if (status == "error")
		{
				$("#alertError").text("Error while saving.");
				$("#alertError").show();
		} else
		{
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
		}
		$("#hidItemIDSave").val("");
		$("#formFeedBack")[0].reset();
	}
}


$(document).on("click", ".btnRemove", function(event)
{
	$.ajax(
	{
		url : "FeedBackAPI",
		type : "DELETE",
		data : "Email=" + $(this).data("Email"),
		dataType : "text",
		complete : function(response, status)
		{
			onItemDeleteComplete(response.responseText, status);
		}
	});
});