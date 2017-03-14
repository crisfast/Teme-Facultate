<%@ Page Title="" Language="C#" MasterPageFile="~/MasterPage.master" AutoEventWireup="true" CodeFile="Editare.aspx.cs" Inherits="Editare" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="Server">
    <div class="adauga1 text-lg-center">
        <div>
            <h3>Editare articol</h3><br />
        </div>
        <div>
            Titlu:
            <asp:textbox id="titluTB" runat="server"></asp:textbox>
            <br /><asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" ErrorMessage="Adaugati un titlu!" ControlToValidate="titluTB" CssClass="eroare-adaugare"></asp:RequiredFieldValidator>
        </div>


        <div>
            Categorie:
            <asp:dropdownlist id="categSelect" runat="server">
                <asp:ListItem Text="Neselectat" Value="Neselectat"></asp:ListItem>
                <asp:ListItem Text="FrontEnd" Value="FrontEnd"></asp:ListItem>
                <asp:ListItem Text="BackEnd" Value="BackEnd"></asp:ListItem>
                <asp:ListItem Text="Software" Value="Software"></asp:ListItem>
            </asp:dropdownlist>
             <br><asp:CompareValidator ID="CompareValidator1" runat="server" ErrorMessage="Alegeti o categorie!" ControlToValidate="categSelect" Operator="NotEqual" ValueToCompare="Neselectat" CssClass="eroare-adaugare"></asp:CompareValidator>
        </div>


        <div>
            Mesaj:
            <br />
            <asp:textbox id="mesajTB" runat="server" textmode="MultiLine" Width="500px" Height="200px"></asp:textbox>
             <br /><asp:RequiredFieldValidator ID="RequiredFieldValidator2" runat="server" ErrorMessage="Campul pentru articol nu poate sa fie gol!" ControlToValidate="mesajTB" CssClass="eroare-adaugare"></asp:RequiredFieldValidator>
        </div>


        <div>
            Poza actuala:
            <br />
            <asp:image id="pozaAct" runat="server" width="10%" height="10%" />
        </div>
        <br />

        <div>
            Actualizeaza Poza:
            <br />
            <asp:fileupload id="FileUpload1" runat="server" />
        </div>
        <br />

        <asp:button id="butonEdit" runat="server" text="Editeaza" onclick="butonTrimite_Click" />

    </div>
</asp:Content>

