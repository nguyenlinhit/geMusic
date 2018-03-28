<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 2/17/2018
  Time: 11:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="../../shared/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title></title>

    <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/plugins/jsgrid-1.3.1/demos.css"/>">
    <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Open+Sans:300,600,400">

    <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/plugins/jsgrid-1.3.1/jsgrid.min.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/plugins/jsgrid-1.3.1/jsgrid-theme.min.css"/>">

</head>
<body>
<h1>Add New Song</h1>
<div id="jsGrid"></div>

<script src="<c:url value="/static/js/jquery-2.1.1.js"/>"></script>
<script src="<c:url value="/static/js/plugins/jsgrid-1.3.1/jsgrid.min.js"/>"></script>
<script>

    $(function() {

        $("#jsGrid").jsGrid({
            height: "90%",
            width: "100%",
            autoload: true,
            deleteConfirm: false,
            paging: true,
            controller: db,
            fields: [
                {
                    headerTemplate: function() {
                        return $("<button>").attr("type", "button").text("Add")
                            .on("click", function() {
                                mappingSelectedItems();
                            });
                    },
                    itemTemplate: function(_, item) {
                        return $("<input>").attr("type", "checkbox")
                            .on("change", function() {
                                $(this).is(":checked") ? selectItem(item) : unselectItem(item);
                            });
                    },
                    align: "center",
                    width: 20
                },
                { name: "id", type: "text", width: 15, title: "ID" },
                { name: "name", type: "text", width: 100, align: "left", title: "Name" },
                { name: "artists", type: "text", width: 80, align: "center", title: "Artists",
                    itemTemplate: function(value) {
                        var artists = "";
                        for (var i = 0; i < value.length; i++) {
                            if (i == 0) {
                                artists += value[i].name;
                            } else if (i == 1) {
                                artists += " ft. " + value[i].name;
                            } else if (i > 1) {
                                artists += " and " + value[i].name;
                            }
                        }
                        return artists;
                    }
                },
                { name: "totalView", type: "number", width: 30, align: "center", title: "Total View" },
            ]
        });

        var selectedItems = [];

        var selectItem = function(item) {
            selectedItems.push(item.id);
        };

        var unselectItem = function(item) {
            selectedItems = $.grep(selectedItems, function(i) {
                return i !== item.id;
            });
        };

        var mappingSelectedItems = function() {
            if (!selectedItems.length)
                return;
            $.post('AddSongs', {
                    songs: selectedItems.join()
                },
                function(response) {
                    if (response != null) {
                        self.close();
                    }
                });

            selectedItems = [];
        };

    });

    var db = {
        loadData: function(filter) {
            return $.ajax({
                type: "GET",
                url: "/rest/song/list",
                data: filter,
                dataType: "json"
            })
        },
    };

</script>
</body>
</html>