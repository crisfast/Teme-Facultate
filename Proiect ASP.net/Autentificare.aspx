<%@ Page Title="" Language="C#" MasterPageFile="~/MasterPage.master" AutoEventWireup="true" CodeFile="Autentificare.aspx.cs" Inherits="Autentificare" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="Server">
    <div style="display: flex; justify-content: center; align-content: center;">
        <asp:Login ID="Login1" runat="server" DestinationPageUrl="~/Default.aspx"></asp:Login>
    </div>
</asp:Content>

