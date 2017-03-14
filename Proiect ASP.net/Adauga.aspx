    <%@ Page Title="" Language="C#" MasterPageFile="~/MasterPage.master" AutoEventWireup="true" CodeFile="Adauga.aspx.cs" Inherits="Adauga" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="Server">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.5/css/bootstrap.min.css" integrity="sha384-AysaV+vQoT3kOAXZkl02PThvDr8HYKPZhNT5h/CXfBThSRXQ6jW5DO2ekP5ViFdi" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.5/js/bootstrap.min.js" integrity="sha384-BLiI7JTZm+JWlgKa0M0kGRpJbF2J8q+qreVrKBC47e3K6BW78kGLrCkeRX6I9RoK" crossorigin="anonymous"></script>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="Server">
    <asp:LoginView ID="LoginView1" runat="server">
        <RoleGroups>
            <asp:RoleGroup Roles="Standard"></asp:RoleGroup>
        </RoleGroups>
        <LoggedInTemplate>

        </LoggedInTemplate>
    </asp:LoginView>
    <div class="adauga1 text-lg-center">
        <div>
            <h3>Adaugare articol</h3><br />
        </div>
        <div>
            Titlu:
            <asp:TextBox ID="titluTB" runat="server"></asp:TextBox>
          <br />  <asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" ErrorMessage="Adaugati un titlu!" ControlToValidate="titluTB" CssClass="eroare-adaugare"></asp:RequiredFieldValidator>
        </div>

        <div>
            Mesaj:
            <br />
            <asp:TextBox ID="mesajTB" runat="server" TextMode="MultiLine" Width="500px" Height="200px"></asp:TextBox>
            <br />  <asp:RequiredFieldValidator ID="RequiredFieldValidator2" runat="server" ErrorMessage="Campul pentru articol nu poate sa fie gol!" ControlToValidate="mesajTB" CssClass="eroare-adaugare"></asp:RequiredFieldValidator>
        </div>

        <div>
            Categorie:
            <asp:DropDownList ID="categSelect" runat="server">
                <asp:ListItem Text="Neselectat" Value="Neselectat" Selected></asp:ListItem>
                <asp:ListItem Text="FrontEnd" Value="FrontEnd"></asp:ListItem>
                <asp:ListItem Text="BackEnd" Value="BackEnd"></asp:ListItem>
                <asp:ListItem Text="Software" Value="Software"></asp:ListItem>
            </asp:DropDownList>
            <br><asp:CompareValidator ID="CompareValidator1" runat="server" ErrorMessage="Alegeti o categorie!" ControlToValidate="categSelect" Operator="NotEqual" ValueToCompare="Neselectat" CssClass="eroare-adaugare"></asp:CompareValidator>
        </div>

        <div>
            Poza post:
            <br />
            <asp:FileUpload ID="FileUpload1" runat="server" />
            <br />
            <asp:RequiredFieldValidator ID="RequiredFieldValidator3" runat="server" ErrorMessage="Adauga o poza!" ControlToValidate="FileUpload1" CssClass="eroare-adaugare"></asp:RequiredFieldValidator>
        </div>
        <br />

        <asp:Button ID="butonTrimite" runat="server" Text="Adauga" OnClick="butonTrimite_Click" />
    </div>
</asp:Content>

