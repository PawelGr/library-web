1. Refaktor kodu - przeniesienie mapowan ponad controller
2. Uzycie GetMapping, PostMapping itp
3. Wypozyczenia/zwroty jako HQL a nie pętli
4. Aplikacja pozostałych uwag do kodu
5. Wykorzystanie pol z encji Adres z user w formularzach i widokach (np apartmentNumber itp)
6. Dodanie dodatkowych reguł walidacyjnych na apartmentNo, streetNo
7. Dodanie checkboxów z kursami (encja Course) 
    - modyfikacja formularza 
    - stworzenie relacji z kursami jako @ManyToMany 
    - checkbox z thymelaf
    - wybrać z widoku ID kursów, przekazać do kontrolera, wyszukać liste obiektów prezz CourseRepository i dopiąć do obiektu User na List<Course>
    - widok do dodawania kursów
8. Odpowiednie katalogi w templates + refactor (strukturyzacja)
9. Tworzenie linków Thymeleaf 
    - edycja usera
    - zwrot ksiazek
    - inne..
10. Zmiana pola author w ksiazce na obiekt Author + relacja + walidacje
11. Rozszerzenie modelu ksiazki o nowe pola:
    - ISBN
    - ilosc stron
    - okladka twarda/miekka (boolean checbox)
    - dorobienie walidacji, szczegolnie do ISBN       