<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html"  pageEncoding="UTF-8"%>
<!--<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">-->
<%@ include file="/WEB-INF/pages/includes.jsp" %>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=10, user-scalable=yes">
        <meta charset="UTF-8">

        <title>Admin Dashboard</title> 
        <script type="text/javascript">
            var Ext = Ext || {}; // Ext namespace won't be defined yet...

            // This function is called by the Microloader after it has performed basic
            // device detection. The results are provided in the "tags" object. You can
            // use these tags here or even add custom tags. These can be used by platform
            // filters in your manifest or by platformConfig expressions in your app.
            //
            Ext.beforeLoad = function (tags) {

                var s = location.search, // the query string (ex "?foo=1&bar")
                        profile;

                // For testing look for "?classic" or "?modern" in the URL to override
                // device detection default.
                //
                if (s.match(/\bclassic\b/)) {
                    profile = 'classic';
                }
                else if (s.match(/\bmodern\b/)) {
                    profile = 'modern';
                }
                // uncomment this if you have added native build profiles to your app.json
                /*else if (tags.webview) {
                 if (tags.ios) {
                 profile = 'ios';
                 }
                 // add other native platforms here
                 }*/
                else {
                    //profile = tags.desktop ? 'classic' : 'modern';
                    profile = tags.phone ? 'modern' : 'classic';
                }

                Ext.manifest = profile; // this name must match a build profile name

                // This function is called once the manifest is available but before
                // any data is pulled from it.
                //
                //return function (manifest) {
                // peek at / modify the manifest object
                //};
            };
        </script>

        <style>
            .preload-body{
                background-image: url(resources/images/lock-screen-background.jpg);
                background-size: cover;
                background-repeat: no-repeat; 
                margin: 0px!important;
            }

            .x-list-plain{ 
                border:1px solid #808080;
            } 

            .x-boundlist-item{
                background-color: white;
                height: 25px;
                padding: 5px;
            }

            .x-boundlist-item-hover{
                background-color:#d0d8ed!important;
            }

            .x-treelist-item-text{
                margin-right: 0px;
            }

            .x-treelist-item-wrap{
                margin-left: 30px!important;
            }

            .x-treelist-navigation .x-treelist-item-text{
                margin-right: 0px!important;
            }

            .x-treelist-row{
                padding-left: 0px!important;
            }
 
            .disabled-button{
                background-color: rgb(192,192,192)!important;
                border-color: rgb(192,192,192)!important;
            }
 
            .active-button{
                background-color:#35baf6!important;
                border-color:#35baf6!important; 
            }
            
            .active-button span span span{
                color:white!important
            }
            
            .check-active{
                background-color:#3b5999!important;
                border-color:#3b5999!important; 
            }
            
            .cash-active{
                background-color:red!important;
                border-color:red!important; 
            }
            
            .card2bank-active{
                background-color:green!important;
                border-color:green!important; 
            }
            
            .commission-active{
                background-color:orange!important;
                border-color:orange!important; 
            }
            
            .check-active span span span,
            .cash-active span span span,
            .card2bank-active span span span,
            .commission-active span span span{
                color:white!important
            }
            
        </style>


        <!-- The line below must be kept intact for Sencha Cmd to build your application -->
        <script id="microloader" type="text/javascript" src="bootstrap.js"></script> 

    </head>
    <body class="preload-body"> 
        <div style="position: absolute; height:44px; background-color:#35baf6; width: 100%; margin: 0px;padding: 0px">
            <span style="width: 100%; height: 100%; position: absolute; text-align: center; color: #f0f0f0;    font-size: 16px;font-weight: 500;line-height: 44px;">Loading...</span>
        </div>

        <img src="resources/images/loading.gif" style="height:120px;width:120px;margin-left:47%;margin-top:240px">
    </body>

</html>
