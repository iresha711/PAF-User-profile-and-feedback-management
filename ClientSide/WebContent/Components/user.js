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
	url : "UserAPI",
	type : type,
	data : $("#formUser").serialize(),
	dataType : "text",
	complete : function(response, status)
		{
			onItemSaveComplete(response.responseText, status);
		}
	});
});

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
	{
		$("#hidItemIDSave").val($(this).closest("tr").find('#hidItemIDUpdate').val());
		$("#NIC").val($(this).closest("tr").find('td:eq(0)').text());
		$("#Name").val($(this).closest("tr").find('td:eq(1)').text());
		$("#Type").val($(this).closest("tr").find('td:eq(2)').text());
		$("#Phone").val($(this).closest("tr").find('td:eq(3)').text());
		$("#Email").val($(this).closest("tr").find('td:eq(4)').text());
		$("#Address").val($(this).closest("tr").find('td:eq(5)').text());
		$("#History").val($(this).closest("tr").find('td:eq(6)').text());
});

// CLIENT-MODEL================================================================
function validateItemForm()
{
	// NIC
	if ($("#NIC").val().trim() == "")
	{
		return "Insert NIC.";
	}
	
	// NAME
	if ($("#Name").val().trim() == "")
	{
		return "Insert Name.";
	}

	// Type-------------------------------
	if ($("#Type").val().trim() == "")
	{
		return "Insert Type.";
	}
	
	// is numerical value phone
	var tmpPhone = $("#Phone").val().trim();
	if (!$.isNumeric(tmpPhone))
	{
		return "Insert Phone.";
	}
	
	// convert to decimal price
	//$("#itemPrice").val(parseFloat(tmpPrice).toFixed(2));
	
	// Email------------------------
	if ($("#Email").val().trim() == "")
	{
		return "Insert Email.";
	}
	
	// Adddress------------------------
	if ($("#Addresss").val().trim() == "")
	{
		return "Insert Addresss.";
	}
	
	// History------------------------
	if ($("#History").val().trim() == "")
	{
		return "Insert History-.";
	}
	return true;
	
	function onItemSaveComplete(response, status)
	{
		if (status == "success")
		{
			var resultSet = JSON.parse(response);
			if (resultSet.status.trim() == "success")
			{
				$("#alertSuccess").text("Successfully saved.");
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
		$("#formUser")[0].reset();
	}
}
$(document).on("click", ".btnUpdate", function(event)
{
	$("#hidItemIDSave").val($(this).data("NIC"));
	$("#Name").val($(this).closest("tr").find('td:eq(0)').text());
	$("#Type").val($(this).closest("tr").find('td:eq(1)').text());
	$("#Phone").val($(this).closest("tr").find('td:eq(2)').text());
	$("#Email").val($(this).closest("tr").find('td:eq(3)').text());
	$("#Address").val($(this).closest("tr").find('td:eq(4)').text());
	$("#History").val($(this).closest("tr").find('td:eq(5)').text());
});

$(document).on("click", ".btnRemove", function(event)
{
	$.ajax(
	{
		url : "UserAPI",
		type : "DELETE",
		data : "NIC=" + $(this).data("NIC"),
		dataType : "text",
		complete : function(response, status)
		{
			onItemDeleteComplete(response.responseText, status);
		}
	});
});
	