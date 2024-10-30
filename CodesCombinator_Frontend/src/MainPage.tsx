import React, { useState } from 'react';
import { HotTable } from '@handsontable/react';
import 'handsontable/dist/handsontable.full.min.css';
import './styles/MainPage.css';
import axios from 'axios';

const MainPageConst: React.FC = () => {
    const [numberOfTables, setNumberOfTables] = useState<number>(3);
    const [tableData, setTableData] = useState(
        Array.from({ length: 3 }, () => [
            ['00934', 'DIN 934 Nakrętka sześciokątna'],
            ['-', '-'],
        ])
    );

    // Stany dla separatorów numerów i nazw
    const [separatorNumbers, setSeparatorNumbers] = useState<string>(',');
    const [customSeparatorNumbers, setCustomSeparatorNumbers] = useState<string>('');

    const [separatorNames, setSeparatorNames] = useState<string>(',');
    const [customSeparatorNames, setCustomSeparatorNames] = useState<string>('');

    const [csvReady, setCsvReady] = useState<boolean>(false);
    const [generatedAt, setGeneratedAt] = useState<string | null>(null);

    const handleTableChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
        const newTableCount = Number(e.target.value);
        setNumberOfTables(newTableCount);
        setTableData(Array.from({ length: newTableCount }, () => [
            ['00934', 'DIN 934 Nakrętka sześciokątna'],
            ['-', '-'],
        ]));
    };

    // Funkcje do zmiany separatora dla numerów i nazw
    const handleSeparatorNumbersChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
        setSeparatorNumbers(e.target.value);
        if (e.target.value !== 'custom') {
            setCustomSeparatorNumbers('');
        }
    };

    const handleCustomSeparatorNumbersChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setCustomSeparatorNumbers(e.target.value);
    };

    const handleSeparatorNamesChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
        setSeparatorNames(e.target.value);
        if (e.target.value !== 'custom') {
            setCustomSeparatorNames('');
        }
    };

    const handleCustomSeparatorNamesChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setCustomSeparatorNames(e.target.value);
    };

    const sendTableData = () => {
        const dataToSend: any = {
            namesSeparator: customSeparatorNames || separatorNames,
            numbersSeparator: customSeparatorNumbers || separatorNumbers,
            NumberTables: {},
            NamesTables: {}
        };

        // Iterujemy przez dane tabeli, aby wypełnić struktury NumberTables i NamesTables
        tableData.forEach((table, index) => {
            // Dodajemy dane z pierwszej kolumny do NumberTables
            dataToSend.NumberTables[`range${index + 1}`] = table.map(row => row[0]);

            // Dodajemy dane z drugiej kolumny do NamesTables
            dataToSend.NamesTables[`range${index + 1}`] = table.map(row => row[1]);
        });

        // Wyświetlanie JSON w konsoli
        console.log("JSON wysyłany do backendu:", JSON.stringify(dataToSend, null, 2));

        // Wysyłanie danych do backendu (odkomentuj, gdy chcesz faktycznie wysyłać dane)
        /*
        axios.post('http://localhost:8080/api/codes/generate', dataToSend)
            .then(response => {
                console.log('CSV został wygenerowany.');
                setCsvReady(true);
                const currentDate = new Date();
                setGeneratedAt(currentDate.toLocaleString());
            })
            .catch(error => {
                console.error('Błąd przy generowaniu CSV:', error);
            });
        */
    };

    // Pobieranie wygenerowanego pliku CSV
    const downloadCsv = () => {
        axios.get('http://localhost:8080/download-csv', { responseType: 'blob' })
            .then(response => {
                const url = window.URL.createObjectURL(new Blob([response.data]));
                const link = document.createElement('a');
                link.href = url;
                link.setAttribute('download', `data_${generatedAt?.replace(/:/g, "-")}.csv`);
                document.body.appendChild(link);
                link.click();
                link.remove();
            })
            .catch(error => {
                console.error('Błąd przy pobieraniu CSV:', error);
            });
    };

    return (
        <div className="MainPage">
            <h1>Panel do wklejania danych</h1>
            <div className="top-controls">
                <label htmlFor="numberOfTables">Wybierz liczbę segmentów:</label>
                <select
                    id="numberOfTables"
                    value={numberOfTables}
                    onChange={handleTableChange}
                >
                    <option value={2}>2</option>
                    <option value={3}>3</option>
                    <option value={4}>4</option>
                    <option value={5}>5</option>
                    <option value={6}>6</option>
                </select>

                {/* Separator dla numerów */}
                <div className="separator-controls">
                    <label htmlFor="separatorNumbers">Wybierz separator dla numerów:</label>
                    <select
                        id="separatorNumbers"
                        value={separatorNumbers}
                        onChange={handleSeparatorNumbersChange}
                    >
                        <option value=",">-</option>
                        <option value=";">_</option>
                        <option value="|">|</option>
                        <option value="custom">Własny separator</option>
                    </select>

                    {separatorNumbers === 'custom' && (
                        <input
                            type="text"
                            placeholder="Wprowadź własny separator"
                            value={customSeparatorNumbers}
                            onChange={handleCustomSeparatorNumbersChange}
                            maxLength={1}
                        />
                    )}
                </div>

                {/* Separator dla nazw */}
                <div className="separator-controls">
                    <label htmlFor="separatorNames">Wybierz separator dla nazw:</label>
                    <select
                        id="separatorNames"
                        value={separatorNames}
                        onChange={handleSeparatorNamesChange}
                    >
                        <option value=","> </option>
                        <option value=";">-</option>
                        <option value="custom">Własny separator</option>
                    </select>

                    {separatorNames === 'custom' && (
                        <input
                            type="text"
                            placeholder="Wprowadź własny separator"
                            value={customSeparatorNames}
                            onChange={handleCustomSeparatorNamesChange}
                            maxLength={1}
                        />
                    )}
                </div>

                <button onClick={sendTableData} className="generate-button">
                    Generuj
                </button>

                <button
                    onClick={downloadCsv}
                    className={`download-button ${csvReady ? 'active' : ''}`}
                    disabled={!csvReady}
                >
                    Pobierz CSV
                </button>

                {generatedAt && (
                    <div className="csv-info">
                        <p>CSV wygenerowany: {generatedAt}</p>
                    </div>
                )}
            </div>

            {[...Array(numberOfTables)].map((_, index) => (
                <div key={index} className="data-table">
                    <HotTable
                        data={tableData[index]}
                        colHeaders={['Numery dla segmentu', 'Nazwa dla numeru']}
                        rowHeaders={true}
                        width="600"
                        height="150"
                        stretchH="all"
                        licenseKey="non-commercial-and-evaluation"
                        manualRowResize={true}
                        manualColumnResize={true}
                        copyPaste={true}
                        allowInsertRow={true}
                        allowInsertColumn={true}
                        allowRemoveRow={true}
                        readOnly={false}
                        undo={true}
                        redo={true}
                        colWidths={150}
                        minSpareRows={1}
                    />
                </div>
            ))}
        </div>
    );
};

export default MainPageConst;