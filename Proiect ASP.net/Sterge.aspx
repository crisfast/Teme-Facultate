<%@ Page Title="" Language="C#" MasterPageFile="~/MasterPage.master" AutoEventWireup="true" CodeFile="Sterge.aspx.cs" Inherits="Sterge" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" Runat="Server">
    <p>
        Esti sigur ca vrei sa stergi articolul "<strong><asp:Label ID="titluLabel" runat="server"></asp:Label></strong>" scris de <strong><asp:Label ID="autorLabel" runat="server"></asp:Label></strong>?
        <br /><br />
        <asp:Button ID="Button1" runat="server" Text="Da" OnClick="Button1_Click"/><asp:Button ID="Button2" runat="server" Text="Anuleaza" OnClick="Button2_Click" />
    </p>
</asp:Content>

